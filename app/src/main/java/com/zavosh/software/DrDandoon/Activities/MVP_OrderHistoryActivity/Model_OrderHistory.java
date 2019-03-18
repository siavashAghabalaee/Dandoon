package com.zavosh.software.DrDandoon.Activities.MVP_OrderHistoryActivity;

import android.content.Context;

import com.zavosh.software.DrDandoon.Helper.CheckResponse;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.MyInterfaces.RequestsManager;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.APIService;
import com.zavosh.software.DrDandoon.Retrofit.ApiUtils;
import com.zavosh.software.DrDandoon.Retrofit.LoginRequest.LoginResult;
import com.zavosh.software.DrDandoon.Retrofit.LoginRequest.LoginSender;
import com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest.PatientOrder;
import com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest.PatientOrderListRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_OrderHistory implements Contract_OrderHistory.Model , RequestsManager {
    private Contract_OrderHistory.Presenter presenter;
    private Context context;
    @Override
    public void attachPresenter(Contract_OrderHistory.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getList() {
        presenter.startLoading();
        getListFromServer();
    }

    private void getListFromServer() {
        APIService apiService = ApiUtils.getAPIService();
        Call<PatientOrderListRequest> patientOrderList = apiService.getPatientOrderList(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""));
        patientOrderList.enqueue(new Callback<PatientOrderListRequest>() {
            @Override
            public void onResponse(Call<PatientOrderListRequest> call, Response<PatientOrderListRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_OrderHistory.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.stopLoading();
                    presenter.setData(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PatientOrderListRequest> call, Throwable t) {
                presenter.setMessageToMonitor(context.getString(R.string.androidErrorRequest));
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        getListFromServer();
    }

    @Override
    public void setMessageForProgressBar(String message, int id) {
        presenter.showMessage(message);
        presenter.setMessageToMonitor(message);
    }
}
