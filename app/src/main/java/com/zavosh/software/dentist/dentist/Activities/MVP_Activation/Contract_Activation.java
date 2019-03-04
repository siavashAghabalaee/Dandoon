package com.zavosh.software.dentist.dentist.Activities.MVP_Activation;

import android.content.Context;

public interface Contract_Activation {
    interface View{
        void showMessage(String message);
        void goBack();
        void startLoadBtn();
        void stopLoadBtn();
        void goHomePage();
    }

    interface Presenter{
        void attachView(View view , Context context);
        void sendClicked(String code);
        void backClicked();
        //-----------
        void showMessage(String message);
        void activated();
        void startLoadBtn();
        void stopLoadBtn();
    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void active(String code);
    }
}
