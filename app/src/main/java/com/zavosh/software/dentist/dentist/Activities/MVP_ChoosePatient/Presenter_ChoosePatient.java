package com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItem;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientItemResult;

import java.util.List;

public class Presenter_ChoosePatient implements Contract_ChoosePatient.Presenter {
    private Contract_ChoosePatient.View view;
    private Model_ChoosePatient model;
    @Override
    public void attachView(Contract_ChoosePatient.View view, Context context) {
        this.view = view;
        model = new Model_ChoosePatient();
        model.attachView(this,context);
    }

    @Override
    public void onResume(String filter) {
        model.getListFromServer(filter);
    }

    @Override
    public void filterClicked() {
        view.showDialogFilter();
        model.getFilterList();
        //dialog faghad load mikone
    }

    @Override
    public void itemFilterClicked(String filterId) {
        model.getListFromServer(filterId);
        view.dismiss();
    }

    @Override
    public void itemClicked(String id) {
        view.goDetailActivity(id);
    }

    @Override
    public void setDataList(List<PatientItemResult> patientList) {
        view.setDataList(patientList);
    }

    @Override
    public void setDataFilter(List<FilterItem> filters) {
        view.setFilterList(filters);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void dismiss() {
        view.dismiss();
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
