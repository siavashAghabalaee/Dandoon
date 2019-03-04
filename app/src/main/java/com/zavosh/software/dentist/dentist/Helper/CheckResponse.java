package com.zavosh.software.dentist.dentist.Helper;

import android.content.Context;
import android.util.Log;

import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.LoginRequest.LoginResult;
import com.zavosh.software.dentist.dentist.Retrofit.LoginRequest.LoginSender;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckResponse {
    public RequestsManager requestsManager;
    public boolean checkStatus(int responseCode , Status status, Context context,int id){
        if (responseCode == 401){
            getToken(context, id);
            return false;
        }else if (responseCode == 200){
            if (status.getStatusCode() == 16){
                getToken(context,id);
                return false;
            }else if (status.getIsSuccess()){
                return true;
            }else {
                MyToast.showToast(context,status.getMessage());
                requestsManager.setMessageForProgressBar(status.getMessage(),id);
                return false;
            }
        }else {
            MyToast.showToast(context,context.getString(R.string.error));
            requestsManager.setMessageForProgressBar(context.getString(R.string.error),id);
            return false;
        }
    }

    public boolean checkRequestCode(int responseCode , Context context,int id){
        if (responseCode == 401){
            getToken(context,id);
            return false;
        }else if (responseCode == 200){
            return true;
        }else {
            MyToast.showToast(context,context.getString(R.string.error));
            requestsManager.setMessageForProgressBar(context.getString(R.string.error),id);
            return false;
        }
    }

    private void getToken(final Context context, final int id) {

        APIService apiService = ApiUtils.getAPIService();
        LoginSender loginSender = new LoginSender(PublicMethods.loadData(PublicMethods.PHONE,""), PublicMethods.loadData(PublicMethods.PASSWORD,""),PublicMethods.loadData(PublicMethods.ROLE,""));

        Call<LoginResult> login = apiService.login(loginSender);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().getIsSuccess()){
                        Log.i("kk","Login Kard");
                        PublicMethods.saveData(PublicMethods.TOKEN_ID,response.body().getResult().getTokenId());
                        requestsManager.resendRequest(id);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                MyToast.showToast(context,context.getString(R.string.androidErrorRequest));
                //requestsManager.setMessageForProgressBar(status.getMessage());
            }
        });


    }
}
