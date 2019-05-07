package getak.app.com.getak.Dialogs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.github.siyamed.shapeimageview.ShapeImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Model.Responses.FastTripResponse.FastTripResModel;
import getak.app.com.getak.R;

public class DriverButtomDialog extends BottomSheetDialog {

    @BindView(R.id.driver_name)
    TextView driverName;
    @BindView(R.id.car_model)
    TextView carModel;
    @BindView(R.id.driver_rate_bar)
    RatingBar driverRate;
    @BindView(R.id.review_value)
    TextView reviewVal;
    @BindView(R.id.total_amount)
    TextView totalVal;
    @BindView(R.id.avatar)
    ShapeImageView avatar;
    @BindView(R.id.call_driver_btn)
    RippleView callDriver;


    public DriverButtomDialog(@NonNull final Context context, final FastTripResModel trip) {
        super(context);
        View contentView = View.inflate(getContext(), R.layout.driver_dialog_button_layout, null);
        ButterKnife.bind(this,contentView);
        setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        driverName.setText(trip.getTrip().getDriver().getDriverName());
        carModel.setText("موديل السيارة : "+trip.getTrip().getDriver().getCarType());
        totalVal.setText(trip.getTrip().getPrice()+"ريال ");
        Picasso.get().load("https://"+trip.getTrip().getDriver().getDriverAvatar()).fit().into(avatar);
        callDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + trip.getTrip().getDriver().getDriverPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity)context), new String[]{Manifest.permission.CALL_PHONE},003);
                }else {
                    context.startActivity(callIntent);
                }
            }
        });
    }

}
