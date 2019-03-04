package com.zavosh.software.dentist.dentist.Activities.MVP_MyPatientList;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient.Model_ChoosePatient;
import com.zavosh.software.dentist.dentist.Helper.CheckResponse;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest.MyPatientListRequest;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientListRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_MyPatientList implements Contract_MyPatientList.Model , RequestsManager {
    private Contract_MyPatientList.Presenter presenter;
    private Context context;
    private String filter;
    @Override
    public void attachView(Contract_MyPatientList.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getListFromServer(String filter) {
        this.filter = filter;
        getListRequest(filter);
    }
    private void getListRequest(String filter) {
        presenter.startLoading();
        APIService apiService = ApiUtils.getAPIService();

        Call<MyPatientListRequest> myPatientList = apiService.getMyPatientList(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""), filter);
        myPatientList.enqueue(new Callback<MyPatientListRequest>() {
            @Override
            public void onResponse(Call<MyPatientListRequest> call, Response<MyPatientListRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_MyPatientList.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.stopLoading();
                    presenter.setDataList(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MyPatientListRequest> call, Throwable t) {
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        switch (id){
            case 1:
                getListFromServer(filter);
                break;
        }
    }

    @Override
    public void setMessageForProgressBar(String message,int id) {
        presenter.stopLoading();
    }
}
