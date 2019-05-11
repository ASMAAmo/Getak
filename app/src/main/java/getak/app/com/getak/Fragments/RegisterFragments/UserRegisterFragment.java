package getak.app.com.getak.Fragments.RegisterFragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import getak.app.com.getak.Activites.MainActivity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRegisterFragment extends Fragment implements AccountView {
    public static KProgressHUD dialog;
    public final String CLIENT="client";

    @BindView(R.id.name_input)
    TextView nameInput;
    @BindView(R.id.phone_input)
    TextView phoneInput;
    @BindView(R.id.email_input)
    TextView emailInput;
    @BindView(R.id.password_input)
    TextView passwordInput;
    @BindView(R.id.re_password_input)
    TextView rePassworInput;
    @BindView(R.id.address_input)
    TextView addressInput;
    @BindView(R.id.age_in)
    TextView ageInput;
    @BindView(R.id.gender)
    RadioGroup genderGroup;

    public static CircleImageView avatar;
    String gender="male";
    public static final int PICK_CLIENT_AVATAR = 2;
    public static String selectedFilePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_register, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        avatar=view.findViewById(R.id.user_avatar);
        dialog=new KProgressHUD(getContext());
        genderGroup.check(R.id.male);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.male:
                        gender = "male";
                        break;
                    case R.id.female:
                        gender = "female";
                        break;
                }
            }
        });
    }
    @OnClick(R.id.user_avatar)
    void pick(){
        pickAvatar();
    }




    @OnClick(R.id.register_btn)
    void register(){
        clientRegisteration();
    }

    void pickAvatar(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        getActivity().startActivityForResult(galleryIntent,PICK_CLIENT_AVATAR);
    }

    void clientRegisteration(){
        nameInput.setError(null);
        phoneInput.setError(null);
        emailInput.setError(null);
        passwordInput.setError(null);
        rePassworInput.setError(null);
        addressInput.setError(null);
        ageInput.setError(null);

        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String rePassword= rePassworInput.getText().toString();
        String address= addressInput.getText().toString();
        String age= ageInput.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError(getString(R.string.reqage));
            focusView = passwordInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(rePassword)) {
            rePassworInput.setError(getString(R.string.passreq));
            focusView = rePassworInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(address)) {
            addressInput.setError(getString(R.string.addressreq));
            focusView = addressInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(age)) {
            ageInput.setError(getString(R.string.agereq));
            focusView = ageInput;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.emailreq));
            focusView = emailInput;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailInput.setError(getString(R.string.emailvalid));
            focusView = emailInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            nameInput.setError(getString(R.string.namereq));
            focusView = nameInput;
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
            RequestBody avatar =null;
            if(selectedFilePath!=null){
                File file = new File(selectedFilePath);
                avatar = RequestBody.create(MediaType.parse("image/*"), file);
            }else {
                avatar = RequestBody.create(MediaType.parse("text/plain"), "");
            }
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("client_name", name)
                    .addFormDataPart("client_email", email)
                    .addFormDataPart("client_phone",phone)
                    .addFormDataPart("client_address",address)
                    .addFormDataPart("client_age",age)
                    .addFormDataPart("client_gender",gender)
                    .addFormDataPart("password",password)
                    .addFormDataPart("password_confirmation",rePassword)
                    .addFormDataPart("deviceType","Android")
                    .addFormDataPart("token", SessionHelper.getPushNotificationToken(getContext()))
                    .addFormDataPart("avatar", "Avatar",avatar)
                    .build();
            AccountPresenter.registerClient(getContext(),requestBody,this);
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }






    @Override
    public void onSuccess(Object obj) {
        if(obj!=null) {
            SessionHelper.setUserType(getContext(),CLIENT);
            UserModel userSession =new UserModel();
            userSession.setClientName(((Result<ClientRegisterationData>)obj).getData().getClient().getClientName());
            userSession.setClientAvatar(((Result<ClientRegisterationData>)obj).getData().getClient().getClientAvatar());
            userSession.setId(((Result<ClientRegisterationData>)obj).getData().getClient().getId());
            SessionHelper.setUserSession(getContext(),userSession);
            Toast.makeText(getContext(),  ((Result<ClientRegisterationData>)obj).getMessage(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(getContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            EventBus.getDefault().post(new LoginEvent(true, ""));
            getActivity().finish();
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
