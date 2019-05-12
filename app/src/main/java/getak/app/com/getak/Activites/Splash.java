package getak.app.com.getak.Activites;

import android.content.Intent;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import io.fabric.sdk.android.Fabric;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
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

