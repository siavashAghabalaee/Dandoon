package com.zavosh.software.dentist.dentist.Activities.MVP_ChooseRole;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Content.Content;

public class Model_ChooseRole implements Contract_ChooseRole.Model {
    private Contract_ChooseRole.Presenter presenter;
    @Override
    public void attachPresenter(Contract_ChooseRole.Presenter presenter, Context context) {
        this.presenter = presenter;
    }

    @Override
    public void doctorClicked() {
        presenter.goNextActivity(Content.DOCTOR);
    }

    @Override
    public void sickClicked() {
        presenter.goNextActivity(Content.SICK);
    }
}
