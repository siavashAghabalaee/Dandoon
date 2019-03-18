package com.zavosh.software.DrDandoon.Activities.MVP_OrderHistoryActivity;

import android.content.Context;

import com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest.PatientOrder;

import java.util.List;

public class Presenter_OrderHistory implements Contract_OrderHistory.Presenter {
    private Contract_OrderHistory.View view;
    private Model_OrderHistory model;
    @Override
    public void attachView(Contract_OrderHistory.View view, Context context) {
        this.view = view;
        model = new Model_OrderHistory();
        model.attachPresenter(this,context);
    }

    @Override
    public void onResume() {
        model.getList();
    }

    @Override
    public void retryClicked() {
        model.getList();
    }

    @Override
    public void setData(List<PatientOrder> data) {
        view.setData(data);
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
}
