package getak.app.com.getak.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Activites.MainActivity;
import getak.app.com.getak.Activites.RegisterActivity;
import getak.app.com.getak.Events.LoginEvent;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Model.Responses.UserModel;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.AccountView;


public class FragmentAccount extends Fragment implements AccountView {
    public static KProgressHUD dialog;
    @BindView(R.id.profile_image)
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    @BindView(R.id.name_input)
    EditText name_input;
    @BindView(R.id.user_name_input)
    EditText user_name_input;
    @BindView(R.id.email_input)
    EditText email_input;
    @BindView(R.id.email_address)
    EditText email_address;
    @BindView(R.id.gender_input)
    EditText gender_input;
    @BindView(R.id.age_input)
    EditText age_input;
    @BindView(R.id.password_input)
    EditText password_input;
    @BindView(R.id.passwordconf_input)
    EditText passwordconf_input;


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
    public void onSuccess(Object obj) {
        if(obj!=null) {
            UserModel profile = ((Result<ClientRegisterationData>)obj).getData().getClient();
         name_input.setText(profile.getClientName());
         user_name_input.setText(profile.getClientPhone());
         email_input.setText(profile.getClientEmail());
         email_address.setText(profile.getClientAddress());
         gender_input.setText(profile.getClientGender());
         age_input.setText(profile.getClientAge()+"");
            if(profile.getClientAvatar()!=null && !profile.getClientAvatar().isEmpty()) {
                Picasso.get().load("https://"+profile.getClientAvatar()).placeholder(R.drawable.ic_person_black_24dp).fit().into(profile_image);
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
