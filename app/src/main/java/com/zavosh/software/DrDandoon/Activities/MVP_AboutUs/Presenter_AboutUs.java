package com.zavosh.software.DrDandoon.Activities.MVP_AboutUs;

import android.content.Context;

public class Presenter_AboutUs implements Contract_AboutUs.Presenter {
    private Contract_AboutUs.View view;
    private Model_AboutUs model;
    @Override
    public void attachView(Contract_AboutUs.View view, Context context) {
        this.view = view;
        model = new Model_AboutUs();
        model.attachPresenter(this,context);
    }

    @Override
    public void onCreate() {
        model.getData();
        view.startLoading();
    }

    @Override
    public void retry() {
        model.getData();
    }

    @Override
    public void setData(String data) {
        view.setData(data);
        view.stopLoading();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void setMessageMonitor(String message) {
        view.setMessageMonitor(message);
    }
}
