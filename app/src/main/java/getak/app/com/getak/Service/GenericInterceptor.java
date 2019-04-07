package getak.app.com.getak.Service;

import android.content.Context;
import android.os.SystemClock;

import java.io.IOException;

import getak.app.com.getak.BuildConfig;
import getak.app.com.getak.Session.SessionHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class GenericInterceptor implements Interceptor {
    private Context context;
    public static final String ANDROID = "0";
    public GenericInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        // Delay Requests with development
        SystemClock.sleep(BuildConfig.DELAY);
        return addRequestHeaders(chain, context);
    }

    private static Response addRequestHeaders(Interceptor.Chain chain, Context context) throws IOException {
        Request request;
        if (SessionHelper.isLogin(context)) {
            request = chain.request().newBuilder()
                    .addHeader("LanguageCode", SessionHelper.getUserLanguageCode(context))
                    .addHeader("RegistrationToken", SessionHelper.getPushNotificationToken(context))
                    .addHeader("OperatingSystemType", ANDROID)
                    .addHeader("OperatingSystemVersion", BuildConfig.VERSION_NAME)
                    .addHeader("Accept", "application/json")
                    .build();
        } else {
            request = chain.request().newBuilder()
                    .addHeader("LanguageCode", SessionHelper.getUserLanguageCode(context))
                    .addHeader("RegistrationToken",  SessionHelper.getPushNotificationToken(context))
                    .addHeader("OperatingSystemType", ANDROID)
                    .addHeader("OperatingSystemVersion", BuildConfig.VERSION_NAME)
                    .addHeader("Accept", "application/json")
                    .build();
        }
        return chain.proceed(request);
    }
}
