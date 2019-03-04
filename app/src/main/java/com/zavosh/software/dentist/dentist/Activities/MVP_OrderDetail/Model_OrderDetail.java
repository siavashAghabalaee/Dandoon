package com.zavosh.software.dentist.dentist.Activities.MVP_OrderDetail;

import android.content.Context;
import android.util.Log;

import com.zavosh.software.dentist.dentist.Helper.CheckResponse;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest.OrderDetailRequest;
import com.zavosh.software.dentist.dentist.Retrofit.PostPurchase.PostPurchaseRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_OrderDetail implements Contract_OrderDetail.Model , RequestsManager {
    private Contract_OrderDetail.Presenter presenter;
    private Context context;
    private String orderId;

    @Override
    public void attachPresenter(Contract_OrderDetail.Presenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;

    }

    @Override
    public void getDataFromServer(String ordeId) {
        this.orderId = ordeId;
        APIService apiService = ApiUtils.getAPIService();
        Call<OrderDetailRequest> orderDetail = apiService.getOrderDetail(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""), ordeId);
        orderDetail.enqueue(new Callback<OrderDetailRequest>() {
            @Override
            public void onResponse(Call<OrderDetailRequest> call, Response<OrderDetailRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_OrderDetail.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.setData(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<OrderDetailRequest> call, Throwable t) {
                presenter.setMessageOnProgressbar(context.getString(R.string.androidErrorRequest));
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void buyFromServer(String orderCode) {
        orderId = orderCode;
        buyRequest(orderCode);
    }

    private void buyRequest(String orderCode) {
        APIService apiService = ApiUtils.getAPIService();
        Call<PostPurchaseRequest> postPurchaseRequestCall = apiService.postPurchase(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""), orderCode);
        postPurchaseRequestCall.enqueue(new Callback<PostPurchaseRequest>() {
            @Override
            public void onResponse(Call<PostPurchaseRequest> call, Response<PostPurchaseRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_OrderDetail.this;
                if (checkResponse.checkRequestCode(response.code(),context,2) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,2)){
                    presenter.setPatientInfo(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostPurchaseRequest> call, Throwable t) {
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        switch (id){
            case 1:
                getDataFromServer(orderId);
                break;
            case 2:
                buyRequest(orderId);
                break;
        }
    }

    @Override
    public void setMessageForProgressBar(String message , int id) {
        switch (id){
            case 1:
                presenter.setMessageOnProgressbar(message);
                presenter.showMessage(message);
                break;
            case 2:
                presenter.showMessage(message);
                presenter.dismiss();
                break;
        }

    }
}
