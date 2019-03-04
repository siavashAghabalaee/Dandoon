package com.zavosh.software.dentist.dentist.Activities.MVP_Register;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Helper.CheckResponse;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.RegisterRequest.RegisterRequest;
import com.zavosh.software.dentist.dentist.Retrofit.RegisterRequest.RegisterSender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_Register implements Contract_Register.Model , RequestsManager {
    private Contract_Register.Presenter presenter;
    private Context context;

    //request data
    private String name;
    private String phone;
    private String role;
    @Override
    public void attachPresenter(Contract_Register.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void register(String name, String phone, String role) {
        if (PublicMethods.isPhone(phone)){
            if (name.length() != 0){
                this.name = name;
                this.phone = phone;
                this.role = role;
                presenter.startLoadBtn();
                registerRequest();
            }else {
                presenter.showMessage(context.getString(R.string.enterFullName));
            }
        }else {
            presenter.showMessage(context.getString(R.string.phoneIsNotValid));
        }
    }

    private void registerRequest() {
        RegisterSender registerSender = new RegisterSender();
        registerSender.setCellNumber(phone); registerSender.setFullName(name); registerSender.setRoleName(role);
        APIService apiService = ApiUtils.getAPIService();
        Call<RegisterRequest> registerRequestCall = apiService.registerRequest(registerSender);
        registerRequestCall.enqueue(new Callback<RegisterRequest>() {
            @Override
            public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                presenter.stopLoadBtn();
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_Register.this;
                if (checkResponse.checkStatus(response.code(), response.body().getStatus(),context,1)){
                    PublicMethods.saveData(PublicMethods.REGISTER_TOKEN,response.body().getResult().getTokenId());
                    PublicMethods.saveData(PublicMethods.PHONE,phone);
                    PublicMethods.saveData(PublicMethods.ROLE,role);
                    presenter.registered(role);
                    presenter.showMessage(response.body().getStatus().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegisterRequest> call, Throwable t) {
                presenter.stopLoadBtn();
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        registerRequest();
        //Log.i("kk","need token");
    }

    @Override
    public void setMessageForProgressBar(String message,int id) {

    }
}
