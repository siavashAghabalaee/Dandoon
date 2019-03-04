package com.zavosh.software.dentist.dentist.Activities.MVP_Login;

import android.content.Context;


public class Presenter_Login implements Contract_Login.Presenter {
    private Contract_Login.View view;
    private Model_Login model;
    @Override
    public void attachView(Contract_Login.View view, Context context) {
        this.view = view;
        model = new Model_Login();
        model.attachPresenter(this,context);
    }

    @Override
    public void loginClicked(String phone, String password, String role) {
        model.loginClicked(phone,password,role);
    }

    @Override
    public void registerClicked() {
        view.goRegisterActivity();
    }

    @Override
    public void forgotPasswordClicked() {
        view.goForgotPassword();
    }

    @Override
    public void backClicked() {
        view.goBack();
    }

    @Override
    public void goHomeDoctor() {
        view.goHomeDoctor();
    }

    @Override
    public void goHomeSick() {
        view.goHomeSick();
    }

    @Override
    public void startLoadBtn() {
        view.startLoadBtn();
    }

    @Override
    public void stopLoadBtn() {
        view.stopLoadBtn();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void goForgotPassword() {
        view.goForgotPassword();
    }

}
