package com.zavosh.software.dentist.dentist.Activities.MVP_HomeDoctor;

import android.content.Context;

import java.util.List;

public class Presenter_HomeDoctor implements Contract_HomeDoctor.Presenter {
    private Contract_HomeDoctor.View view;
    private Model_HomeDoctor model;
    @Override
    public void attachView(Contract_HomeDoctor.View view, Context context) {
        this.view = view;
        this.model = new Model_HomeDoctor();
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
