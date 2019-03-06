package com.zavosh.software.DrDandoon.Activities.MVP_Activation;

import android.content.Context;

public class Presenter_Activation implements Contract_Activation.Presenter {
    private Contract_Activation.View view;
    private Model_Activation model;
    @Override
    public void attachView(Contract_Activation.View view, Context context) {
        this.view = view;
        model = new Model_Activation();
        model.attachPresenter(this,context);
    }

    @Override
    public void sendClicked(String code) {
        model.active(code);
    }

    @Override
    public void backClicked() {
        view.goBack();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void activated() {
        view.goHomePage();
    }

    @Override
    public void startLoadBtn() {
        view.startLoadBtn();
    }

    @Override
    public void stopLoadBtn() {
        view.stopLoadBtn();
    }
}
