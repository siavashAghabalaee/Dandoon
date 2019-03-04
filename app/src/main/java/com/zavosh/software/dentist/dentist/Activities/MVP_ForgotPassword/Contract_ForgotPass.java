package com.zavosh.software.dentist.dentist.Activities.MVP_ForgotPassword;

import android.content.Context;

public interface Contract_ForgotPass {
    interface View{
        void showMessage(String message);
        void goBack();
        void freeze();
        void unFreeze();
        void startLoadBtn();
        void stopLoadBtn();
    }

    interface Presenter{
        void attachView(View view , Context context);
        void sendClicked(String phone,String role);
        void backClicked();
        //-----------
        void showMessage(String message);
        void freeze();
        void unFreeze();
        void passwordRecovered(String message);
        void startLoadBtn();
        void stopLoadBtn();
    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void passwordRecovery(String phone,String role);
    }
}
