package com.zavosh.software.DrDandoon.Activities.MVP_HomePatient;

import android.content.Context;

import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.Helper.CheckResponse;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.MyInterfaces.RequestsManager;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.APIService;
import com.zavosh.software.DrDandoon.Retrofit.ApiUtils;
import com.zavosh.software.DrDandoon.Retrofit.HomeSickRequest.HomeSickRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_HomePatient implements Contract_HomePatient.Model , RequestsManager {
    private Contract_HomePatient.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract_HomePatient.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getData() {
        getHome();
    }

    private void getHome() {
        APIService apiService = ApiUtils.getAPIService();
        Call<HomeSickRequest> homeSickRequestCall = apiService.homeSickRequest(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""), PublicMethods.getAppVersion(context), Content.OSTYPE);
        homeSickRequestCall.enqueue(new Callback<HomeSickRequest>() {
            @Override
            public void onResponse(Call<HomeSickRequest> call, Response<HomeSickRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_HomePatient.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.loadedData(response.body().getResult().getHeaderImages(),response.body().getResult().getCellNumber(),response.body().getResult().getFullName(),"");
                    presenter.hideProgressBarr();
                }
            }

            @Override
            public void onFailure(Call<HomeSickRequest> call, Throwable t) {
                presenter.setMessageInMonitor(context.getString(R.string.androidErrorRequest));
            }
        });

    }

    @Override
    public void resendRequest(int id) {
        getHome();
    }

    @Override
    public void setMessageForProgressBar(String message,int id) {
        presenter.setMessageInMonitor(message);
    }
}
