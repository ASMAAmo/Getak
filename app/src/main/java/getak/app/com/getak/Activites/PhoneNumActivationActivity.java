package getak.app.com.getak.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;

public class PhoneNumActivationActivity extends BaseActivity {
    @BindView(R.id.activation_code_input)
    EditText codeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_num_activation);
        ButterKnife.bind(this);
        setupCodeInput();

    }

    private void setupCodeInput() {
        codeInput.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int len=s.toString().length();
                if (before == 0 && (len == 2 || len == 7 || len == 9 ))
                    codeInput.append("   ");
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick(R.id.confirm_btn)
    void confirm(){
        startActivity(new Intent(PhoneNumActivationActivity.this,RegisterActivity.class));
    }
}
