package com.zavosh.software.dentist.dentist.Activities.MVP_Splash;

import android.content.Context;

public interface Contract_Splash {
    interface View{
        void showMessage(String message);
        void showRetry();
        void showUpdate(String link,boolean isNecessary);
        void goHomeSick();
        void goHomeDoctor();
        void goChooseRole();
        void hideLoader();
        void showLoader();

    }

    interface Presenter{
        void attachView(View view , Context context);
        void onResume();
        void retryClicked();
        //--------------------
        void showMessage(String message);
        void showRetry();
        void showUpdate(String link,boolean isNecessary);
        void goHomeSick();
        void goHomeDoctor();
        void goChooseRole();
        void hideLoader();
        void showLoader();


    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void startTimerCheckVersion();
        void checkVersion();

    }
}
