package com.zavosh.software.DrDandoon.Activities.MVP_HomeDoctor;

import android.content.Context;
import android.content.Intent;

import com.zavosh.software.DrDandoon.Activities.MVP_AboutUs.AboutUsActivity;
import com.zavosh.software.DrDandoon.Activities.MVP_Support.SupportActivity;
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

public class Model_HomeDoctor implements Contract_HomeDoctor.Model ,RequestsManager {
    private Contract_HomeDoctor.Presenter presenter;
    private Context context;
    private String inviteText = "";
    private String aboutUsText = "";
    private String supportPhone = "";

    @Override
    public void attachPresenter(Contract_HomeDoctor.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getData() {
        getHome();
    }

    @Override
    public void inviteFriend() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, inviteText);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    @Override
    public void goAboutUs() {
        Intent intent = new Intent(context,AboutUsActivity.class);
        intent.putExtra(Content.ABOUT_KEY,aboutUsText);
        context.startActivity(intent);
    }

    @Override
    public void goSupport() {
        Intent intent = new Intent(context,SupportActivity.class);
        intent.putExtra(Content.SUPPORT_PHONE,supportPhone);
        context.startActivity(intent);
    }

    private void getHome() {
        APIService apiService = ApiUtils.getAPIService();
        Call<HomeSickRequest> homeSickRequestCall = apiService.homeSickRequest(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""), PublicMethods.getAppVersion(context), Content.OSTYPE);
        homeSickRequestCall.enqueue(new Callback<HomeSickRequest>() {
            @Override
            public void onResponse(Call<HomeSickRequest> call, Response<HomeSickRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_HomeDoctor.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    inviteText = response.body().getResult().getInviteText();
                    aboutUsText = response.body().getResult().getAboutText();
                    supportPhone = response.body().getResult().getSupportPhone();
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
    public void setMessageForProgressBar(String message ,int id) {
        presenter.setMessageInMonitor(message);
    }
}
