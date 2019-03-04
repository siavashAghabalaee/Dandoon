package com.zavosh.software.dentist.dentist.Activities.MVP_ToothSelection;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyCity;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyState;

import java.util.List;

public class Presenter_ToothSelection implements Contract_ToothSelection.Presenter {
    private Contract_ToothSelection.View view;
    private Model_ToothSelection model;
    @Override
    public void attachView(Contract_ToothSelection.View view, Context context) {
        this.view = view;
        this.model = new Model_ToothSelection();
        model.attachPresenter(this,context);
    }

    @Override
    public void nextClicked(boolean ageType, int toothNumber) {
        model.checkValue(ageType,toothNumber);
    }

    @Override
    public void chooseCityClicked() {
        model.getState();
        view.showDialogChooseCity();
    }

    @Override
    public void acceptClicked(int stateOrCity,int filter) {
        model.acceptClicked(stateOrCity,filter);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void valueIsOk(boolean ageType, int toothNumber , String cityId) {
        view.goNext(ageType,toothNumber , cityId);
    }

    @Override
    public void setState(List<MyState> stateList) {
        view.setStateList(stateList);
    }

    @Override
    public void setCity(List<MyCity> cityList) {
        view.setCity(cityList);
    }

    @Override
    public void loadDialog() {
        view.loadDialog();
    }

    @Override
    public void setCityName(String cityName) {
        view.setCityName(cityName);

    }

}
