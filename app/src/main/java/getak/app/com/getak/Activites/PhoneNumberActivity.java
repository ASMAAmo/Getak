package getak.app.com.getak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;

public class PhoneNumberActivity extends BaseActivity {
    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.code_input)
    EditText codeInput;
    @BindView(R.id.keys_spinner)
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        ButterKnife.bind(this);
        codeInput.setEnabled(false);
        codeInput.setText(countryCodePicker.getSelectedCountryCode());
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                codeInput.setText(countryCodePicker.getSelectedCountryCode());
            }
        });
    }


    @OnClick(R.id.next_btn)
    void next(){
        startActivity(new Intent(PhoneNumberActivity.this,PhoneNumActivationActivity.class));
    }
}
