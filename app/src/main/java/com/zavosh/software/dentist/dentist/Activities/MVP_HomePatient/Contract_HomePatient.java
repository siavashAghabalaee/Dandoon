package com.zavosh.software.dentist.dentist.Activities.MVP_HomePatient;

import android.content.Context;

import java.util.List;

public interface Contract_HomePatient {
    interface View{
        void showMessage(String message);
        void stopSlider();
        void showProgressBar();
        void hideProgressBarr();
        void setMessageInMonitor(String message);
        void setData(List<String> images , String phone , String name , String imageProfile);
    }

    interface Presenter{
        void attachView(View view , Context context);
        void onResume();
        void onPause();
        void retryClicked();
        //-----------
        void loadedData(List<String> images , String phone , String name , String imageProfile);
        void showMessage(String message);
        void setMessageInMonitor(String message);
        void hideProgressBarr();
    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void getData();
    }
}
