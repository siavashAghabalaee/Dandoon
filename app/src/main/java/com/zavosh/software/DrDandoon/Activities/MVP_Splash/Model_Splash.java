package com.zavosh.software.DrDandoon.Activities.MVP_Splash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.Helper.CheckResponse;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.Helper.Version;
import com.zavosh.software.DrDandoon.MyInterfaces.RequestsManager;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.APIService;
import com.zavosh.software.DrDandoon.Retrofit.ApiUtils;
import com.zavosh.software.DrDandoon.Retrofit.CheckVersionRequest.CheckVersionRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_Splash implements Contract_Splash.Model ,RequestsManager {

    private Contract_Splash.Presenter presenter;
    private Context context;
    private Handler handler;
    private Runnable runnable;
    @Override
    public void attachPresenter(Contract_Splash.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                checkVersionRequest();
            }
        };
    }

    @Override
    public void startTimerCheckVersion() {
        handler.postDelayed(runnable,3000);
    }

    @Override
    public void checkVersion() {
        checkVersionRequest();
    }

    public void checkVersionRequest(){
        APIService apiService = ApiUtils.getAPIService();
        Call<CheckVersionRequest> checkVersionRequestCall = apiService.checkVersionRequest(Content.OSTYPE);
        checkVersionRequestCall.enqueue(new Callback<CheckVersionRequest>() {
            @Override
            public void onResponse(Call<CheckVersionRequest> call, Response<CheckVersionRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_Splash.this;
                if (checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    try {
                        PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                        String string_currentVersion = pInfo.versionName;
                        String string_latestStableVersion = response.body().getResult().getLatestStableVersion();
                        String string_versionNumber = response.body().getResult().getVersionNumber();

                        Version currentVersion = new Version(string_currentVersion);
                        Version latestStableVersion = new Version(string_latestStableVersion);
                        Version versionNumber = new Version(string_versionNumber);

                        manageVersion(currentVersion, latestStableVersion, versionNumber, response.body().getResult().getIsBeta(), response.body().getResult().getLink());
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckVersionRequest> call, Throwable t) {
                presenter.showRetry();
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

    private void manageVersion(Version currentVersion, Version latestStableVersion, Version versionNumber, boolean beta, String link) {
        if (currentVersion.compareTo(latestStableVersion) == -1){
            //currentVersion < latestStableVersion
            presenter.showUpdate(link,true);
        }else {
            if (currentVersion.compareTo(versionNumber) == -1){
                //currentVersion < versionNumber
                if (beta){
                    if (PublicMethods.loadData(PublicMethods.ROLE,"").equals(Content.DOCTOR)){
                        presenter.goHomeDoctor();
                    }else if (PublicMethods.loadData(PublicMethods.ROLE,"").equals(Content.SICK)){
                        presenter.goHomeSick();
                    }else {
                        presenter.goChooseRole();
                    }
                }else {
                    presenter.showUpdate(link,false);
                }
            }else {
                if (PublicMethods.loadData(PublicMethods.ROLE,"").equals(Content.DOCTOR)){
                    presenter.goHomeDoctor();
                }else if (PublicMethods.loadData(PublicMethods.ROLE,"").equals(Content.SICK)){
                    presenter.goHomeSick();
                }else {
                    presenter.goChooseRole();
                }
            }
        }
    }
}
