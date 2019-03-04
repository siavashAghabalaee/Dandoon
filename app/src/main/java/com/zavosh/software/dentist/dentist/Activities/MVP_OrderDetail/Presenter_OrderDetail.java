package com.zavosh.software.dentist.dentist.Activities.MVP_OrderDetail;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest.OrderDetailResult;
import com.zavosh.software.dentist.dentist.Retrofit.PostPurchase.PostPurchaseResult;

public class Presenter_OrderDetail implements Contract_OrderDetail.Presenter {
    private Contract_OrderDetail.View view;
    private Model_OrderDetail model;
    @Override
    public void attachView(Contract_OrderDetail.View view, Context context) {
        this.view = view;
        model = new Model_OrderDetail();
        model.attachPresenter(this,context);
    }

    @Override
    public void onCreate(String orderId) {
        model.getDataFromServer(orderId);
        view.loadMyProgressbar();
    }

    @Override
    public void retryClicked(String orderId) {
        model.getDataFromServer(orderId);
        view.loadMyProgressbar();
    }

    @Override
    public void buyClicked(String orderCode) {
        model.buyFromServer(orderCode);
        view.showDialogPatientInfo();
    }

    @Override
    public void setData(OrderDetailResult data) {
        view.setData(data);
        view.stopMyProgressbar();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void setMessageOnProgressbar(String message) {
        view.setMessageOnProgressbar(message);
    }

    @Override
    public void setPatientInfo(PostPurchaseResult result) {
        view.setPatientInfo(result);
    }

    @Override
    public void dismiss() {
        view.dismiss();
    }
}
