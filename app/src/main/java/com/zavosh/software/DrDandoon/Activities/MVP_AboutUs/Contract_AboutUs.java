package com.zavosh.software.DrDandoon.Activities.MVP_AboutUs;

import android.content.Context;

public interface Contract_AboutUs {
    interface View{
        void showMessage(String message);
        void setData(String data);
        void startLoading();
        void stopLoading();
        void setMessageMonitor(String message);
    }
    interface Presenter{
        void attachView(View view, Context context);
        void onCreate();
        void retry();

        //---------------------
        void setData(String data);
        void showMessage(String message);
        void setMessageMonitor(String message);


    }
    interface Model{
        void attachPresenter(Presenter presenter,Context context);
        void getData();

    }
}
