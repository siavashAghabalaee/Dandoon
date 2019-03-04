package com.zavosh.software.dentist.dentist.Activities.MVP_ToothSelection;

import android.content.Context;
import android.util.Log;

import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.Helper.CheckResponse;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.MyInterfaces.RequestsManager;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.APIService;
import com.zavosh.software.dentist.dentist.Retrofit.ApiUtils;
import com.zavosh.software.dentist.dentist.Retrofit.LoginRequest.LoginResult;
import com.zavosh.software.dentist.dentist.Retrofit.LoginRequest.LoginSender;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.CityRequest;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyCity;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyState;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.StateRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_ToothSelection implements Contract_ToothSelection.Model , RequestsManager {
    private Contract_ToothSelection.Presenter presenter;
    private Context context;
    private int state = -1;
    private int city = 0;
    private List<MyState> stateList;
    private List<MyCity> cityList;

    @Override
    public void attachPresenter(Contract_ToothSelection.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void checkValue(boolean ageType, int toothNumber) {
        if (state == -1 || city == -1){
            presenter.showMessage(context.getString(R.string.chooseCity));
        }else {
            ToothSelectionActivity.orderSender.setCityId(cityList.get(city).getId());
            ToothSelectionActivity.orderSender.setNumber(toothNumber+"");
            ToothSelectionActivity.orderSender.setIsKid(ageType+"");
            presenter.valueIsOk(ageType,toothNumber , cityList.get(city).getCity());
        }

    }

    @Override
    public void getState() {

        stateRequest();
    }

    @Override
    public void acceptClicked(int stateOrCity , int filter) {
        if (filter == 0){
            state = stateOrCity;
            presenter.loadDialog();
            requestCity();
        }else {
            city = stateOrCity;
            presenter.setCityName(cityList.get(city).getCity());
        }
    }

    private void requestCity() {
        APIService apiService = ApiUtils.getAPIService();
        Call<CityRequest> city = apiService.getCity(stateList.get(state).getId());
        //Call<CityRequest> city = apiService.getCity("bee9f901-ca91-4a67-8114-3ca5e6e40e90");
        Log.i("kk",stateList.get(state).getId());
        city.enqueue(new Callback<CityRequest>() {
            @Override
            public void onResponse(Call<CityRequest> call, Response<CityRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_ToothSelection.this;
                cityList = response.body().getResult();
                if (checkResponse.checkStatus(response.code(),response.body().getStatus(),context,2)){
                    presenter.setCity(cityList);
                    Log.i("kk","ok");
                }
            }

            @Override
            public void onFailure(Call<CityRequest> call, Throwable t) {
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
            }
        });

    }

    public void stateRequest(){
        APIService apiService = ApiUtils.getAPIService();
        Call<StateRequest> state = apiService.getState();
        state.enqueue(new Callback<StateRequest>() {
            @Override
            public void onResponse(Call<StateRequest> call, Response<StateRequest> response) {
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_ToothSelection.this;
                stateList = response.body().getResult();
                if (checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.setState(stateList);
                }
            }

            @Override
            public void onFailure(Call<StateRequest> call, Throwable t) {
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
