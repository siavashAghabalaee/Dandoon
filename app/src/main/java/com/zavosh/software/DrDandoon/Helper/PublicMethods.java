package com.zavosh.software.DrDandoon.Helper;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.hawk.Hawk;

import java.text.NumberFormat;
import java.util.Locale;

public class PublicMethods {
    public static String TOKEN_ID = "tokenId";
    public static String PHONE = "phone";
    public static String PASSWORD = "password";
    public static String ROLE = "role";
    public static String REGISTER_CODE = "REGISTER_CODE";
    public static String REGISTER_TOKEN = "REGISTER_TOKEN";
    public static String _device;

    public static void saveData(String key , String value){
        Hawk.put ( key , value );
    }

    public static String loadData(String key,String def){
        String value = Hawk.get(key);
        if (value == null){
            return def;
        }else {
            return value;
        }
    }

    public static void clearAllData(){
        Hawk.deleteAll();
    }

    public static String convert(String price){
        String newPrice = price.trim();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String numberAsString = numberFormat.format(Integer.parseInt(newPrice));
        return numberAsString;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;

    }

    public static boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPhone(String phone){
        if (StringUtility.isValidPhoneNumber(phone)){
            return true;
        }else {
            return false;
        }
    }

    public static String GetDeviceString() {
        if (_device != null)
            return _device;
        if (Build.MODEL.contains(Build.MANUFACTURER)) {
            _device = Build.MODEL;
            return _device;
        }
        _device = Build.MANUFACTURER + " " + Build.MODEL;
        return _device;
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getAndroidApi(){
        return String.valueOf(android.os.Build.VERSION.SDK_INT);
    }

    public static String getAppVersion(Context context){
        String version = "0.0.0";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
