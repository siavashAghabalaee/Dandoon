package com.zavosh.software.DrDandoon.Activities.MVP_Activation;

import android.content.Context;
import android.util.Log;

import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.Helper.CheckResponse;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.MyInterfaces.RequestsManager;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.APIService;
import com.zavosh.software.DrDandoon.Retrofit.ActivationRequest.ActivationRequest;
import com.zavosh.software.DrDandoon.Retrofit.ActivationRequest.ActivationSender;
import com.zavosh.software.DrDandoon.Retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_Activation implements Contract_Activation.Model , RequestsManager {
    private Contract_Activation.Presenter presenter;
    private Context context;
    private String code;

    @Override
    public void attachPresenter(Contract_Activation.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void active(String code) {
        if (code.length()>0){
            this.code = code;
            presenter.startLoadBtn();
            activationRequest();
        }else {
            presenter.showMessage(context.getString(R.string.enterCode));
        }
    }

    private void activationRequest() {
        ActivationSender activationSender = new ActivationSender(
                PublicMethods.loadData(PublicMethods.REGISTER_TOKEN,""),
                code,PublicMethods.getDeviceId(context),
                PublicMethods.GetDeviceString(),
                Content.OSTYPE,
                PublicMethods.getAndroidApi());

        Log.i("kk",activationSender.toString());

        APIService apiService = ApiUtils.getAPIService();
        Call<ActivationRequest> activationRequestCall = apiService.activationRequest(activationSender);
        activationRequestCall.enqueue(new Callback<ActivationRequest>() {
            @Override
            public void onResponse(Call<ActivationRequest> call, Response<ActivationRequest> response) {
                presenter.stopLoadBtn();
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_Activation.this;
                if (checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    PublicMethods.saveData(PublicMethods.TOKEN_ID,response.body().getResult().getTokenId());
                    PublicMethods.saveData(PublicMethods.PASSWORD,code);
                    presenter.activated();
                }
            }

            @Override
            public void onFailure(Call<ActivationRequest> call, Throwable t) {
                presenter.stopLoadBtn();
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        activationRequest();
    }

    @Override
    public void setMessageForProgressBar(String message,int id) {
    }
}
