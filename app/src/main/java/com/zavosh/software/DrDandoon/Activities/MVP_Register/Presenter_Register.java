package com.zavosh.software.DrDandoon.Activities.MVP_Register;

import android.content.Context;

public class Presenter_Register implements Contract_Register.Presenter{
    private Contract_Register.View view;
    private Model_Register model;
    @Override
    public void attachView(Contract_Register.View view, Context context) {
        this.view = view;
        this.model = new Model_Register();
        model.attachPresenter(this,context);
    }

    @Override
    public void backClicked() {
        view.goBack();
    }

    @Override
    public void goLoginClicked() {
        view.goBack();
    }

    @Override
    public void registerClicked(String name, String phone, String role) {
        model.register(name,phone,role);
    }

    @Override
    public void registered(String role) {
        view.goActivationPage(role);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
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
