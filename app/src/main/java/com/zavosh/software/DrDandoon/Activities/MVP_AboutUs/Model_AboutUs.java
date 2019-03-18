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
        presenter.setData("");
    }

    @Override
    public void resendRequest(int id) {

    }

    @Override
    public void setMessageForProgressBar(String message, int id) {
        presenter.setMessageMonitor(message);
    }
}
