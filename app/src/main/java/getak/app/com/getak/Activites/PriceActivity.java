package getak.app.com.getak.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;

public class PriceActivity extends BaseActivity {
   @BindView(R.id.price_tv)
   TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        ButterKnife.bind(this);
        price.setText(getIntent().getStringExtra("price")+" ريال");
    }

    @OnClick(R.id.end_price_btn)
    void end(){
        finish();
    }

}
