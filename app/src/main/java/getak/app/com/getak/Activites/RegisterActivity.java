package getak.app.com.getak.Activites;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.reg_type_tab)
    TabLayout regTypeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        regTypeSwitch.addTab(regTypeSwitch.newTab().setText(R.string.cust));
        regTypeSwitch.addTab(regTypeSwitch.newTab().setText(R.string.driv));
        regTypeSwitch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

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
