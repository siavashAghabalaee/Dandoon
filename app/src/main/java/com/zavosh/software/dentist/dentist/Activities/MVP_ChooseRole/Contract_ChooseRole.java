package com.zavosh.software.dentist.dentist.Activities.MVP_ChooseRole;

import android.content.Context;

public interface Contract_ChooseRole {
    interface View{
        void goNextActivity(String role);
    }

    interface Presenter{
        void attachView(View view , Context context);
        void doctorClicked();
        void sickClicked();
        //-----------
        void goNextActivity(String role);
    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void doctorClicked();
        void sickClicked();
    }
}
