package com.zavosh.software.DrDandoon.Activities.MVP_Sapport;

import android.content.Context;

public interface Contract_Support {
    interface View{
        void showMessage(String message);
        void goBack();
        void goToCall(String phone);
        void startLoading();
        void stopLoading();
        void setMessageToMonitor(String message);
    }

    interface Presenter{
        void attachView(View view , Context context);
        void sendClicked(String subject , String description);
        void callClicked(String phone);

        //--------------------
        void showMessage(String message);
        void startLoading();
        void stopLoading();
        void setMessageToMonitor(String message);
        void posted();
    }
    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void sendMessage(String subject , String description);
    }
}
