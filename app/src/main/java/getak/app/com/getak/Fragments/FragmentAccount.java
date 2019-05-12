package getak.app.com.getak.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Events.LoginEvent;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Model.Responses.UserModel;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.AccountView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class FragmentAccount extends Fragment implements AccountView {
    public static KProgressHUD dialog;
    @BindView(R.id.profile_image)
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    @BindView(R.id.name_input)
    EditText name_input;
    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.email_address)
    EditText email_input;
    @BindView(R.id.address_input)
    EditText addressInput;
    @BindView(R.id.gender)
    RadioGroup gender;
    @BindView(R.id.age_in)
    EditText age_input;
    @BindView(R.id.password_input)
    TextView passwordInput;
    @BindView(R.id.re_password_input)
    TextView rePassworInput;
    String clientGender="";
    String selectedFilePath;


    @Override
    public void onStart() {
        super.onStart();
        dialog=new KProgressHUD(getContext());
        AccountPresenter.getClientProfile(getContext(),SessionHelper.getUserSession(getContext()).getId(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_account, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.saveBtn)
    void save(){
        doUpdateProfile();
    }


    void doUpdateProfile (){
        name_input.setError(null);
        phoneInput.setError(null);
        email_input.setError(null);
        addressInput.setError(null);
        age_input.setError(null);
        passwordInput.setError(null);
        rePassworInput.setError(null);

        String name = name_input.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = email_input.getText().toString();
        String address= addressInput.getText().toString();
        String age= age_input.getText().toString();
        String password =passwordInput.getText().toString();
        String rePassword =rePassworInput.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(address)) {
            addressInput.setError(getString(R.string.addressreq));
            focusView = addressInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            passwordInput.setError(getString(R.string.passreq));
            focusView = passwordInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(rePassword)) {
            rePassworInput.setError(getString(R.string.passreq));
            focusView = rePassworInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(age)) {
            age_input.setError(getString(R.string.agereq));
            focusView = age_input;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_input.setError(getString(R.string.emailreq));
            focusView = email_input;
            cancel = true;
        } else if (!isEmailValid(email)) {
            email_input.setError(getString(R.string.emailvalid));
            focusView = email_input;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            name_input.setError(getString(R.string.namereq));
            focusView = name_input;
            cancel = true;
        }

        if (TextUtils.isEmpty(phone)) {
            phoneInput.setError(getString(R.string.phonereq));
            focusView = phoneInput;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt registration and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("client_name", name)
                    .addFormDataPart("client_email", email)
                    .addFormDataPart("client_phone",phone)
                    .addFormDataPart("client_address",address)
                    .addFormDataPart("client_age",age)
                    .addFormDataPart("password",password)
                    .addFormDataPart("password_confirmation",rePassword)
                    .addFormDataPart("client_gender",clientGender)
                    .build();
            AccountPresenter.updateClientProfile(getContext(),SessionHelper.getUserSession(getContext()).getId(),requestBody,this);
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    @Override
    public void onSuccess(Object obj) {
        if(obj!=null) {
            UserModel profile = ((Result<ClientRegisterationData>)obj).getData().getClient();
            SessionHelper.setUserSession(getContext(),profile);
         name_input.setText(profile.getClientName());
         phoneInput.setText(profile.getClientPhone());
         email_input.setText(profile.getClientEmail());
         addressInput.setText(profile.getClientAddress());
            EventBus.getDefault().post(new LoginEvent(true,""));
       if(profile.getClientGender()!=null) {
           if (profile.getClientGender().equals("male")) {
               gender.check(R.id.male);
               clientGender = "male";
           } else {
               gender.check(R.id.female);
               clientGender = "female";
           }
       }
            gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId){
                        case R.id.male:
                            clientGender = "male";
                            break;
                        case R.id.female:
                            clientGender = "female";
                            break;
                    }
                }
            });

         age_input.setText(profile.getClientAge()+"");
            if(profile.getClientAvatar()!=null && !profile.getClientAvatar().isEmpty()) {
                Picasso.get().load(profile.getClientAvatar()).placeholder(R.drawable.ic_person_black_24dp).fit().into(profile_image);
            }

    }

    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(getContext(),err,Toast.LENGTH_LONG).show();
    }

    @Override
    public void loading(boolean status) {
        if(status){
            dialog.show();
        }else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
