package getak.app.com.getak.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import getak.app.com.getak.Model.Responses.UserModel;

public class SessionHelper {
    private static final String SHARED_PREFERENCES_FILE = "com.getak.sharedpreferences";
    private static final String NOTIFICATIONS_SHAREDPREFS="notificationsSharedPres";
    private static final String ACCESS_TOKEN = SHARED_PREFERENCES_FILE + ".accesstoken";
    private static final String LANGUAGE = SHARED_PREFERENCES_FILE + ".language";
    private static final String PUSH_TOKEN = SHARED_PREFERENCES_FILE + ".pushtoken";
    private static final String USER_TYPE = SHARED_PREFERENCES_FILE + ".usertype";
    private static final String ARABIC = "عربى";
    private static final String ENGLISH = "English";

    private static final String AR = "ar";
    private static final String EN = "en";
    private static final String USER_INFO = SHARED_PREFERENCES_FILE + ".userinfo";

    private static UserModel userSession;




    public static void setUserSession(Context context, UserModel fUserSession) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(fUserSession);
        editor.putString(USER_INFO, json);
        editor.apply();
        userSession = fUserSession;
    }


    @NonNull
    public static UserModel getUserSession(Context context) {
        if (userSession == null) {
            SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
            String json = sharedPref.getString(USER_INFO, null);
            if (json != null) {
                Type type = new TypeToken<UserModel>() {
                }.getType();
                Gson gson = new Gson();
                userSession = gson.fromJson(json, type);
            }
        }
        return userSession;
    }



    public static void setUserLanguageSession(Context context, String Language, OnSessionUpdate onSessionUpdate) {
        if (!getUserLanguage(context).equals(Language)) {
            SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(LANGUAGE, Language);
            editor.apply();
            setLocale(getUserLanguageCode(context), context);
            onSessionUpdate.refreshActivity();
        }
    }


    public static String getUserLanguage(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        String lang = sharedPref.getString(LANGUAGE, null);
        return lang != null ? lang : ENGLISH;
    }


    public static Context configLanguage(Context context) {
        return setLocale(getUserLanguageCode(context), context);
    }

    public static String getUserLanguageCode(Context context) {
        return getLanguagesCodes().get(getLanguageIndex(context));
    }

    //Check if English
    public static boolean isEnglish(Context context) {
        return getUserLanguage(context).equals(ENGLISH);
    }

    //Check if Arabic
    public static boolean isArabic(Context context) {
        return getUserLanguage(context).equals(ARABIC);
    }



    //English
    public static void setLanguageEnglish(Context context, OnSessionUpdate onSessionUpdate) {
        setUserLanguageSession(context, ENGLISH, onSessionUpdate);
    }

    //Arabic
    public static void setLanguageArabic(Context context, OnSessionUpdate onSessionUpdate) {
        setUserLanguageSession(context, ARABIC, onSessionUpdate);
    }


    public static List<String> getLanguages() {
        return Arrays.asList(ARABIC, ENGLISH);
    }
    private static List<String> getLanguagesCodes() {
        return Arrays.asList(AR, EN);
    }
    public static int getLanguageIndex(Context context) {
        int index = getLanguages().indexOf(getUserLanguage(context));
        return index == -1 ? 1 : index;
    }


    //Change localization Method
    private static Context setLocale(String lang, Context context) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        //configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }




    public static void savePushNotificationToken(Context context, String token) {
        SharedPreferences sharedPref = context.getSharedPreferences(NOTIFICATIONS_SHAREDPREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PUSH_TOKEN, token);
        editor.apply();
    }

    public static String getPushNotificationToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(NOTIFICATIONS_SHAREDPREFS, Context.MODE_PRIVATE);
        return sharedPref.getString(PUSH_TOKEN, "");
    }


    public static void setUserType(Context context, String type) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_TYPE, type);
        editor.apply();
    }


    public static boolean isDriver(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(USER_TYPE, "").equals("driver");
    }


    public static String getUserType(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(USER_TYPE, "");
    }


    public void saveKeyValue(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getValueByKey(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }
    public static boolean isLogin(Context context) {
        return getUserSession(context) != null;
    }

    public static void logout(Context context, SessionCallBack sessionCallBack) {
        // Clear saved data
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        // Reset session
        userSession = null;
        // Notify
        sessionCallBack.setOnLogout();
    }





    public interface OnSessionUpdate {
        void refreshActivity();
    }

    public interface SessionCallBack {
        void setOnLogout();
    }
}
