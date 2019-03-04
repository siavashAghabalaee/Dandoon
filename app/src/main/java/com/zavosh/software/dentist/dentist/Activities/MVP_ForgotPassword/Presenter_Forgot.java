package com.zavosh.software.dentist.dentist.Activities.MVP_ForgotPassword;

import android.content.Context;

public class Presenter_Forgot implements Contract_ForgotPass.Presenter {
    private Contract_ForgotPass.View view;
    private Model_Forgot model;
    @Override
    public void attachView(Contract_ForgotPass.View view, Context context) {
        this.view = view;
        model = new Model_Forgot();
        model.attachPresenter(this,context);
    }

    @Override
    public void sendClicked(String phone,String role) {
        model.passwordRecovery(phone,role);
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
    public void freeze() {
        view.freeze();
    }

    @Override
    public void unFreeze() {
        view.unFreeze();
    }

    @Override
    public void passwordRecovered(String message) {
        view.showMessage(message);
        view.goBack();
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
