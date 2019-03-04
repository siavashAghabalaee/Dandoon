package com.zavosh.software.dentist.dentist.Activities.MVP_ForgotPassword;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.zavosh.software.dentist.dentist.Helper.CheckResponse;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.ForgotPasRequest.ForgotPassResult;
import com.zavosh.software.dentist.dentist.Retrofit.ForgotPasRequest.ForgotPassSender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_Forgot implements Contract_ForgotPass.Model , RequestsManager {
    private Contract_ForgotPass.Presenter  presenter;
    private Context context;
    private String phone;
    private String role;
    @Override
    public void attachPresenter(Contract_ForgotPass.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void passwordRecovery(String phone,String role) {
        if (PublicMethods.isPhone(phone)){
            this.phone = phone;
            this.role = role;
            Log.i("kk",phone);
            presenter.startLoadBtn();
            requestRecoveryPass(this.phone,role);
        }else {
            presenter.showMessage(context.getString(R.string.phoneIsNotValid));
        }
    }

    public void requestRecoveryPass(String phone , String role){
        presenter.freeze();
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String deviceModel = PublicMethods.GetDeviceString();
        String osType = "Android";
        String osVersion = String.valueOf(Build.VERSION.SDK_INT);
        ForgotPassSender forgetPassSender =new ForgotPassSender();
        forgetPassSender.setCellNumber(phone);
        forgetPassSender.setRoleName(role);
        forgetPassSender.setDeviceId(deviceId);
        forgetPassSender.setDeviceModel(deviceModel);
        forgetPassSender.setOsType(osType);
        forgetPassSender.setOsVersion(osVersion);
        APIService apiService = ApiUtils.getAPIService();
        Call<ForgotPassResult> forgotPassResultCall = apiService.forgetPassRequest(forgetPassSender);
        forgotPassResultCall.enqueue(new Callback<ForgotPassResult>() {
            @Override
            public void onResponse(Call<ForgotPassResult> call, Response<ForgotPassResult> response) {
                presenter.unFreeze();

                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_Forgot.this;
                if (checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.passwordRecovered(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ForgotPassResult> call, Throwable t) {
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
                presenter.stopLoadBtn();
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        requestRecoveryPass(phone,role);
    }

    @Override
    public void setMessageForProgressBar(String message , int id) {

    }
}
