package getak.app.com.getak.Activites;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Events.LoginEvent;
import getak.app.com.getak.Model.Responses.LoginResponse.ClientLoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.AccountView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity implements AccountView {

    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String TOKEN = "token";
    public static final String LOGIN_TYPE = "loginType";
    @BindView(R.id.login_type_tab)
    TabLayout loginTypeSwitch;
    @BindView(R.id.login_lbl)
    TextView loginLbl;
    @BindView(R.id.user_name_input)
    EditText userNameInput;
    @BindView(R.id.password_input)
    EditText passwordInput;
    String loginType="client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginTypeSwitch.addTab(loginTypeSwitch.newTab().setText(R.string.cust));
        loginTypeSwitch.addTab(loginTypeSwitch.newTab().setText(R.string.driv));
        loginTypeSwitch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : {
                        loginLbl.setText(getString(R.string.custlogin));
                        loginType="client";
                        break;
                    }

                    case 1 : {
                        loginLbl.setText(getString(R.string.driverlogin));
                        loginType="driver";
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @OnClick(R.id.login_btn)
    void doLogin(){
        userNameInput.setError(null);
        passwordInput.setError(null);
        String username = userNameInput.getText().toString();
        String password = passwordInput.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError(getString(R.string.passreq));
            focusView = passwordInput;
            cancel = true;
        }
        if (TextUtils.isEmpty(username)) {
            userNameInput.setError(getString(R.string.usernamereq));
            focusView = userNameInput;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt registration and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(PHONE, username)
                    .addFormDataPart(PASSWORD, password)
                    .addFormDataPart(DEVICE_TYPE,"Android")
                    .addFormDataPart(TOKEN,SessionHelper.getPushNotificationToken(this))
                    .addFormDataPart(LOGIN_TYPE,loginType)
                    .build();
            AccountPresenter.clientLogin(this,requestBody,this);
        }
    }

    @Override
    public void onSuccess(Object obj) {
        if(obj!=null) {
            SessionHelper.setUserSession(this, ((Result<ClientLoginResponse>)obj).getData().getClient());
            Toast.makeText(this,  ((Result<ClientLoginResponse>)obj).getMessage(), Toast.LENGTH_LONG).show();
            EventBus.getDefault().post(new LoginEvent(true, ""));
            LoginActivity.this.finish();
        }
    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(this,err,Toast.LENGTH_LONG).show();
    }

    @Override
    public void loading(boolean status) {

    }
}
