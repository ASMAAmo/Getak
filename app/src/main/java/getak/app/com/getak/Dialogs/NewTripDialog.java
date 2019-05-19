package getak.app.com.getak.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Events.AcceptTripEvent;
import getak.app.com.getak.Model.NotificationModels.DriverNotification;
import getak.app.com.getak.Presenters.IncomingTripsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.IncomingTripInteraction;

public class NewTripDialog extends BottomSheetDialog implements IncomingTripInteraction {
    DriverNotification driverNotification;
    Context context;
    public static MediaPlayer mediaPlayer;
    public NewTripDialog(@NonNull Context context, DriverNotification driverNotification) {
        super(context);
        View contentView = View.inflate(getContext(), R.layout.new_trip_dialog, null);
        ButterKnife.bind(this,contentView);
        setContentView(contentView);
//        mediaPlayer = MediaPlayer.create(context, R.raw.plucky);
//        mediaPlayer.setVolume(100,100);
//        if(!mediaPlayer.isPlaying()){
//            mediaPlayer.start();
//        }
        ((View) contentView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        Gson gson =new Gson();
        Log.e("DATATA",gson.toJson(driverNotification));
        this.driverNotification=driverNotification;
        this.context=context;
    }


    @OnClick(R.id.accept_btn)
    void accepted(){
        HashMap request =new HashMap();
        request.put("trip_id",driverNotification.getTrip().getId());
        request.put("driver_id", SessionHelper.getUserSession(getContext()).getId());
        IncomingTripsPresenter.acceptTrip(context,request,this);
    }

    @OnClick(R.id.cancel_btn)
    void canceled(){
dismiss();
    }





    @Override
    public void onAccepted(String msg) {
        EventBus.getDefault().post(new AcceptTripEvent(driverNotification));
        dismiss();
    }

    @Override
    public void onCanceled(String msg) {

    }

    @Override
    public void onError(String err) {
        Toast.makeText(context, err, Toast.LENGTH_SHORT).show();
        dismiss();
    }


}
