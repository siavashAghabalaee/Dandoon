package com.zavosh.software.DrDandoon.Activities.MVP_Login;

import android.content.Context;

import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.Helper.CheckResponse;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.MyInterfaces.RequestsManager;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.APIService;
import com.zavosh.software.DrDandoon.Retrofit.ApiUtils;
import com.zavosh.software.DrDandoon.Retrofit.LoginRequest.LoginResult;
import com.zavosh.software.DrDandoon.Retrofit.LoginRequest.LoginSender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Model_Login implements Contract_Login.Model , RequestsManager {
    private Contract_Login.Presenter presenter;
    private Context context;
    private String phone;
    private String password;
    private String role;
    @Override
    public void attachPresenter(Contract_Login.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void loginClicked(String phone, String password, String role) {
        if (phone.length() > 0 && password.length() > 0 && role.length() > 0){
            if (PublicMethods.isPhone(phone)){
                this.phone = phone;
                this.password = password;
                this.role = role;
                presenter.startLoadBtn();
                requestLogin(phone,password,role);
            }else {
                presenter.showMessage(context.getString(R.string.phoneIsNotValid));
            }
        }else {
            presenter.showMessage(context.getString(R.string.enterYourData));
        }
    }

    public void requestLogin (final String phone, final String password, final String role){
        APIService apiService = ApiUtils.getAPIService();
        LoginSender loginSender = new LoginSender(phone,password,role);
        Call<LoginResult> login = apiService.login(loginSender);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                presenter.stopLoadBtn();
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_Login.this;
                if (checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    PublicMethods.saveData(PublicMethods.TOKEN_ID,response.body().getResult().getTokenId());
                    PublicMethods.saveData(PublicMethods.PHONE,phone);
                    PublicMethods.saveData(PublicMethods.PASSWORD,password);
                    PublicMethods.saveData(PublicMethods.ROLE,role);
                    if (role.equals(Content.DOCTOR)){
                        presenter.goHomeDoctor();
                    }else {
                        presenter.goHomeSick();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                presenter.stopLoadBtn();
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {

    }

    @Override
    public void setMessageForProgressBar(String message,int id) {
    }
}
