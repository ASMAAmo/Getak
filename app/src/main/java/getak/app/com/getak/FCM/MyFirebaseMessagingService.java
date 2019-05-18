package getak.app.com.getak.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import getak.app.com.getak.Activites.PriceActivity;
import getak.app.com.getak.Dialogs.NewTripDialog;
import getak.app.com.getak.Events.EndTripForCustomers;
import getak.app.com.getak.Events.NotificationsEvent;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private NotificationChannel mChannel;
    private NotificationManager notifManager;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Check if message contains a data payload.

        if (remoteMessage.getData().size() > 0) {
            try {
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }


            //End trip for user
            if(SessionHelper.isLogin(getApplicationContext())&&!SessionHelper.isDriver(getApplicationContext())){
                if(remoteMessage.getData().get("type").equals("5")){
                    EventBus.getDefault().post(new EndTripForCustomers(SessionHelper.getUserTripInfo(getApplicationContext()).getPrice()+""));
                    startActivity(new Intent(getApplicationContext(), PriceActivity.class).putExtra("price",SessionHelper.getUserTripInfo(getApplicationContext()).getPrice()+"").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    SessionHelper.setUserTripInfo(getApplicationContext(),null);

                }
            }



            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            EventBus.getDefault().post(new NotificationsEvent(remoteMessage.getData().toString()));
            String title = remoteMessage.getNotification().getTitle();
            String body = "";
            String message ="";
            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + body);
            String click_action = remoteMessage.getNotification().getClickAction();
            notify(title,body,message,click_action);

        }
    }

    public void notify(String title, String description, String message, String click_action) {

        if (notifManager == null) {
            notifManager = (NotificationManager) getSystemService
                    (Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (mChannel == null) {
                mChannel = new NotificationChannel
                        ("0", title, importance);
                mChannel.setDescription(description);
                mChannel.enableVibration(true);
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, "0");
            Bitmap bitmapIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification);
            builder.setContentTitle(title)
                    .setSmallIcon(getNotificationIcon()) // required
                    .setContentText(description)  // required
                    .setLargeIcon(bitmapIcon)
                    .setColorized(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setBadgeIconType(R.drawable.ic_notification)
                    .setSound(RingtoneManager.getDefaultUri
                            (RingtoneManager.TYPE_NOTIFICATION));
            Notification notification = builder.build();
            notifManager.notify(0, notification);
        } else {
            Bitmap bitmapIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setContentText(description)
                    .setAutoCancel(true)
                    .setLargeIcon(bitmapIcon)
                    .setColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary))
                    .setColorized(true)
                    .setSound(defaultSoundUri)
                    .setSmallIcon(getNotificationIcon())
                    .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title).bigText(description));

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1251, notificationBuilder.build());
        }
    }

    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_notification : R.drawable.ic_notification;
    }


}
