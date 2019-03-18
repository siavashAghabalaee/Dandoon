package com.zavosh.software.DrDandoon.Helper;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;
import com.crashlytics.android.answers.SignUpEvent;

public class FabricSender {

    public static void login(){
        Answers.getInstance().logLogin(new LoginEvent().putMethod("Log In").putSuccess(true)
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Data",PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi())
        );
    }

    public static void logout (){
        Answers.getInstance().logCustom(new CustomEvent("Log Out")
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Data",PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi()));
    }

    public static void home (){
        Answers.getInstance().logCustom(new CustomEvent("Home")
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Role",PublicMethods.loadData(PublicMethods.ROLE,"role"))
                .putCustomAttribute("Data",PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi()));
    }

    public static void Register(){
        Answers.getInstance().logSignUp(new SignUpEvent().putMethod("Sign Up").putSuccess(true)
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Role",PublicMethods.loadData(PublicMethods.ROLE,"role"))
                .putCustomAttribute("Data",PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi())
        );
    }

    public static void cancel (String orderCode){
        Answers.getInstance().logCustom(new CustomEvent("Cancel")
                .putCustomAttribute("Order Code",orderCode)
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Data",orderCode+"   "+PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi()));
    }

    public static void addOrder (){
        Answers.getInstance().logCustom(new CustomEvent("New Order")
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Data",PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi()));
    }

    public static void accept (String orderCode){
        Answers.getInstance().logCustom(new CustomEvent("Accept Order")
                .putCustomAttribute("Order Code",orderCode)
                .putCustomAttribute("Phone Number",PublicMethods.getPhone())
                .putCustomAttribute("Device Model",PublicMethods.getDeviceString())
                .putCustomAttribute("Api",PublicMethods.getAndroidApi())
                .putCustomAttribute("Data",orderCode+"   "+PublicMethods.getPhone()+"   "+PublicMethods.getDeviceString()+"   "+PublicMethods.getAndroidApi()));
    }

}
