package getak.app.com.getak.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Events.NotificationsEvent;
import getak.app.com.getak.Model.NotificationModels.Client;
import getak.app.com.getak.Model.NotificationModels.DriverNotification;
import getak.app.com.getak.Model.NotificationModels.Trip;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import io.fabric.sdk.android.Fabric;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (SessionHelper.isLogin(this)) {
                if(SessionHelper.isDriver(this)) {
                    if (bundle.getString("trip") != null && bundle.getString("client") != null) {
                        String trip = bundle.getString("trip");
                        String client = bundle.getString("client");
                        Gson gson = new Gson();
                        Trip tripModel = gson.fromJson(trip, Trip.class);
                        Client clientModel = gson.fromJson(client, Client.class);
                        DriverNotification driverNotification = new DriverNotification();
                        driverNotification.setClient(clientModel);
                        driverNotification.setTrip(tripModel);
                        Splash.this.finish();
                        startActivity(new Intent(Splash.this, MainActivity.class)
                                .putExtra("payload", gson.toJson(driverNotification))
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }
                if(!SessionHelper.isDriver(this)) {
                    if (bundle.getString("type").equals("5")) {
                        startActivity(new Intent(this, PriceActivity.class).putExtra("price", SessionHelper.getUserTripInfo(this).getPrice() + "").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        SessionHelper.setUserTripInfo(this, null);
                        finish();
                    }
                }
            }
        }else {
            Thread mythread = new Thread() {
                public void run() {
                    try {
                        int mythread = 0;
                        while (mythread < 3500) {
                            sleep(100);
                            mythread = mythread + 100;
                        }
                        startActivity(new Intent(Splash.this, IntroActivity.class));

                    } catch (InterruptedException ie) {
                        ie.printStackTrace();

                    } finally {
                        finish();
                    }
                }

            };
            mythread.start();
        }
      }
    }

