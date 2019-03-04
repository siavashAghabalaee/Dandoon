package com.zavosh.software.dentist.dentist.Activities.MVP_Login;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Activities.MVP_ChooseRole.Contract_ChooseRole;

public interface Contract_Login {
    interface View{
        void goHomeDoctor();
        void goHomeSick();
        void showMessage(String message);
        void goForgotPassword();
        void goRegisterActivity();
        void goBack();
        void startLoadBtn();
        void stopLoadBtn();
    }

    interface Presenter{
        void attachView(View view , Context context);
        void loginClicked(String Phone , String password , String role);
        void registerClicked();
        void forgotPasswordClicked();
        void backClicked();
        //-----------
        void goHomeDoctor();
        void goHomeSick();
        void startLoadBtn();
        void stopLoadBtn();
        void showMessage(String message);
        void goForgotPassword();
    }

    interface Model{
        void attachPresenter(Presenter view , Context context);
        void loginClicked(String phone , String password , String role);
    }
}
