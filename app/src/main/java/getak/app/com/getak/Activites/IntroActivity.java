package getak.app.com.getak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SessionHelper.isLogin(this)){
            startActivity(new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            IntroActivity.this.finish();
        }
    }

    //Skip button Action
    @OnClick(R.id.skip_btn)
    void skip(){
        startActivity(new Intent(IntroActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    //Login button Action
    @OnClick(R.id.login_btn)
    void login(){
        startActivity(new Intent(IntroActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    //Login button Action
    @OnClick(R.id.register_btn)
    void register(){
        startActivity(new Intent(IntroActivity.this,RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

}
