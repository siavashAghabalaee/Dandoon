package com.zavosh.software.DrDandoon.Activities.MVP_Sapport;

import android.content.Context;

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

public class Model_Support implements Contract_Support.Model , RequestsManager {
    private Contract_Support.Presenter presenter;
    private Context context;
    private String subject,description;

    @Override
    public void attachPresenter(Contract_Support.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;

    }

    @Override
    public void sendMessage(String subject, String description) {
        if (subject.length()>0 && description.length()>0){
            this.subject = subject;
            this.description = description;
            sendToServer(subject,description);
            presenter.startLoading();
        }else {
            presenter.showMessage(context.getString(R.string.setSubjectAndDescription));
        }
    }

    private void sendToServer(String subject, String description) {
        APIService apiService = ApiUtils.getAPIService();
        LoginSender loginSender = new LoginSender(PublicMethods.loadData(PublicMethods.PHONE,""), PublicMethods.loadData(PublicMethods.PASSWORD,""),PublicMethods.loadData(PublicMethods.ROLE,""));

        Call<LoginResult> login = apiService.login(loginSender);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().getIsSuccess()){
                        presenter.posted();
                        presenter.showMessage(response.body().getStatus().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                presenter.setMessageToMonitor(context.getString(R.string.androidErrorRequest));
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        sendToServer(subject,description);
    }

    @Override
    public void setMessageForProgressBar(String message, int id) {
        presenter.showMessage(message);
        presenter.setMessageToMonitor(message);
    }
}
