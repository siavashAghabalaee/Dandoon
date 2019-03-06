package com.zavosh.software.DrDandoon.Activities.MVP_Splash;

import android.content.Context;

public class Presenter_Splash implements Contract_Splash.Presenter {
    private Contract_Splash.View view;
    private Model_Splash model;
    @Override
    public void attachView(Contract_Splash.View view, Context context) {
        this.view = view;
        model = new Model_Splash();
        model.attachPresenter(this,context);
    }

    @Override
    public void onResume() {
        model.startTimerCheckVersion();
    }

    @Override
    public void retryClicked() {
        model.checkVersion();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void showRetry() {
        view.showRetry();
    }

    @Override
    public void showUpdate(String link,boolean isNecessary) {
        view.showUpdate(link,isNecessary);
    }

    @Override
    public void goHomeSick() {
        view.goHomeSick();
    }

    @Override
    public void goHomeDoctor() {
        view.goHomeDoctor();
    }

    @Override
    public void goChooseRole() {
        view.goChooseRole();
    }

    @Override
    public void hideLoader() {
        view.hideLoader();
    }

    @Override
    public void showLoader() {
        view.showLoader();
    }
}
