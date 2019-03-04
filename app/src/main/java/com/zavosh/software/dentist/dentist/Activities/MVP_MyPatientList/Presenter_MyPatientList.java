package com.zavosh.software.dentist.dentist.Activities.MVP_MyPatientList;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest.MyPatientListResult;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientItemResult;

import java.util.List;

public class Presenter_MyPatientList implements Contract_MyPatientList.Presenter {
    private Contract_MyPatientList.View view;
    private Model_MyPatientList model;
    @Override
    public void attachView(Contract_MyPatientList.View view, Context context) {
        this.view = view;
        model = new Model_MyPatientList();
        model.attachView(this,context);
    }

    @Override
    public void onResume(String filter) {
        model.getListFromServer(filter);
    }

    @Override
    public void setDataList(List<MyPatientListResult> patientList) {
        view.setDataList(patientList);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void stopLoading() {
        view.stopLoading();
    }

    @Override
    public void startLoading() {
        view.startLoading();
    }
}
