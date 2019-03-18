package com.zavosh.software.DrDandoon.Activities.MVP_Support;

import android.content.Context;

public class Presenter_Support implements Contract_Support.Presenter {
    private Contract_Support.View view;
    private Model_Support model;

    @Override
    public void attachView(Contract_Support.View view, Context context) {
        this.view = view;
        model = new Model_Support();
        model.attachPresenter(this,context);
    }

    @Override
    public void sendClicked(String subject, String description) {
        model.sendMessage(subject,description);
    }

    @Override
    public void callClicked(String phone) {
        view.goToCall(phone);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void startLoading() {
        view.startLoading();
    }

    @Override
    public void stopLoading() {
        view.stopLoading();
    }

    @Override
    public void setMessageToMonitor(String message) {
        view.setMessageToMonitor(message);
    }

    @Override
    public void posted() {
        view.goBack();
    }
}
