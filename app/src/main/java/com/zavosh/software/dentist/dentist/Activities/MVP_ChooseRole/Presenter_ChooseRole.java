package com.zavosh.software.dentist.dentist.Activities.MVP_ChooseRole;

import android.content.Context;

public class Presenter_ChooseRole implements Contract_ChooseRole.Presenter {
    private Contract_ChooseRole.View view;
    private Model_ChooseRole model;

    @Override
    public void attachView(Contract_ChooseRole.View view, Context context) {
        this.view = view;
        model = new Model_ChooseRole();
        model.attachPresenter(this,context);
    }

    @Override
    public void doctorClicked() {
        model.doctorClicked();
    }

    @Override
    public void sickClicked() {
        model.sickClicked();
    }

    @Override
    public void goNextActivity(String role) {
        view.goNextActivity(role);
    }
}
