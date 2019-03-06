package com.zavosh.software.DrDandoon.Activities.MVP_AboutUs;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zavosh.software.DrDandoon.CustomViews.MyToast;
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

public class Model_AboutUs implements Contract_AboutUs.Model , RequestsManager {
    private Contract_AboutUs.Presenter presenter;
    private Context context;
    @Override
    public void attachPresenter(Contract_AboutUs.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        APIService apiService = ApiUtils.getAPIService();
        LoginSender loginSender = new LoginSender(PublicMethods.loadData(PublicMethods.PHONE,""), PublicMethods.loadData(PublicMethods.PASSWORD,""),PublicMethods.loadData(PublicMethods.ROLE,""));

        Call<LoginResult> login = apiService.login(loginSender);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().getIsSuccess()){
                        presenter.setData("namayeshe matn");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                presenter.setMessageMonitor(context.getString(R.string.androidErrorRequest));
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        switch (id){
            case 1:
                //TODO
                break;
        }
    }

    @Override
    public void setMessageForProgressBar(String message, int id) {
        presenter.setMessageMonitor(message);
    }
}
