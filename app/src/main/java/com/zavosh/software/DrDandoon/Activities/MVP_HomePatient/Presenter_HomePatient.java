package com.zavosh.software.DrDandoon.Activities.MVP_HomePatient;

import android.content.Context;

import java.util.List;

public class Presenter_HomePatient implements Contract_HomePatient.Presenter {
    private Contract_HomePatient.View view;
    private Model_HomePatient model;
    @Override
    public void attachView(Contract_HomePatient.View view, Context context) {
        this.view = view;
        this.model = new Model_HomePatient();
        model.attachPresenter(this,context);
    }

    @Override
    public void onResume() {
        view.showProgressBar();
        model.getData();
    }

    @Override
    public void onPause() {
        view.stopSlider();
    }

    @Override
    public void retryClicked() {
        model.getData();
        view.showProgressBar();
    }

    @Override
    public void inviteClicked() {
        model.inviteFriend();
    }

    @Override
    public void aboutUsClicked() {
        model.goAboutUs();
    }

    @Override
    public void supportClicked() {
        model.goSupport();
    }

    @Override
    public void loadedData(List<String> images, String phone, String name, String imageProfile) {
        view.setData(images,phone,name,imageProfile);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void setMessageInMonitor(String message) {
        view.setMessageInMonitor(message);
    }

    @Override
    public void hideProgressBarr() {
        view.hideProgressBarr();
    }
}
