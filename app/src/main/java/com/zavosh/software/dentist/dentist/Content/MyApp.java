package com.zavosh.software.dentist.dentist.Content;

import android.app.Application;
import android.graphics.Typeface;

import com.orhanobut.hawk.Hawk;

public class MyApp extends Application {
    public static Typeface appFont;
    @Override
    public void onCreate() {
        super.onCreate();
        appFont = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum)_Light.ttf");
        Hawk.init(this).build();
    }
}
