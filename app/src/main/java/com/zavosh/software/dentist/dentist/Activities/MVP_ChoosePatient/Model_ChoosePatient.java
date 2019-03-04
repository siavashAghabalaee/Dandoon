package com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Helper.CheckResponse;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItem;
import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItemRequest;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientListRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_ChoosePatient implements Contract_ChoosePatient.Model , RequestsManager {
    private Contract_ChoosePatient.Presenter presenter;
    private Context context;
    private String filter;
    @Override
    public void attachView(Contract_ChoosePatient.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getListFromServer(String filter) {
        this.filter = filter;
        getListRequest(filter);
    }

    @Override
    public void getFilterList() {
        getFilterRequest();
    }

    private void getFilterRequest() {
        APIService apiService = ApiUtils.getAPIService();
        Call<FilterItemRequest> filterList = apiService.getFilterList(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""));
        filterList.enqueue(new Callback<FilterItemRequest>() {
            @Override
            public void onResponse(Call<FilterItemRequest> call, Response<FilterItemRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_ChoosePatient.this;
                if (checkResponse.checkRequestCode(response.code(),context,2) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,2)){

                    List<FilterItem> myItems = new ArrayList<>();
                    FilterItem filterItem = new FilterItem();filterItem.setId("all");filterItem.setTitle("همه");
                    myItems.add(filterItem);
                    for (int i = 0; i < response.body().getResult().size(); i++) {
                        myItems.add(response.body().getResult().get(i));
                    }
                    presenter.setDataFilter(myItems);
                }
            }

            @Override
            public void onFailure(Call<FilterItemRequest> call, Throwable t) {
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
                presenter.dismiss();
            }
        });
    }

    private void getListRequest(String filter) {
        presenter.startLoading();
        APIService apiService = ApiUtils.getAPIService();
        Call<PatientListRequest> patientList = apiService.getPatientList(PublicMethods.loadData(PublicMethods.TOKEN_ID, ""), filter);
        patientList.enqueue(new Callback<PatientListRequest>() {
            @Override
            public void onResponse(Call<PatientListRequest> call, Response<PatientListRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_ChoosePatient.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.stopLoading();
                    presenter.setDataList(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PatientListRequest> call, Throwable t) {
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
            case 2:
                getFilterRequest();
                break;
        }

    }

    @Override
    public void setMessageForProgressBar(String message , int id) {
        presenter.stopLoading();
    }
}
