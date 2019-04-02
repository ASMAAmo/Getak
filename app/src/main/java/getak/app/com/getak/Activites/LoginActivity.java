package getak.app.com.getak.Activites;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_type_tab)
    TabLayout loginTypeSwitch;
    @BindView(R.id.login_lbl)
    TextView loginLbl;
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
                        break;
                    }

                    case 1 : {
                        loginLbl.setText(getString(R.string.driverlogin));
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
}
