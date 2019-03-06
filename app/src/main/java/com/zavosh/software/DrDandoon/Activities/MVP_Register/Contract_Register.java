package com.zavosh.software.DrDandoon.Activities.MVP_Register;

import android.content.Context;

public interface Contract_Register {
    interface View {
        void showMessage(String message);
        void goActivationPage(String role);
        void goBack();
        void startLoadBtn();
        void stopLoadBtn();
    }

    interface Presenter{
        void attachView(View view , Context context);
        void backClicked();
        void goLoginClicked();
        void registerClicked(String name , String phone , String role);
        //------------------
        void registered(String role);
        void showMessage(String message);
        void startLoadBtn();
        void stopLoadBtn();
    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void register(String name , String phone , String role);
    }
}
