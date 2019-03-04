package com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItem;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientItemResult;

import java.util.List;

public interface Contract_ChoosePatient {
    interface View {
        void setDataList(List<PatientItemResult> patientList);
        void showDialogFilter();
        void setFilterList(List<FilterItem>  filters);
        void showMessage(String message);
        void dismiss();
        void stopLoading();
        void startLoading();
        void goDetailActivity(String id);

    }

    interface Presenter{
        void attachView (View view, Context context);
        void onResume(String filter);
        void filterClicked();
        void itemFilterClicked(String filterId);
        void itemClicked(String id);
        //--------------------
        void setDataList(List<PatientItemResult> patientList);
        void setDataFilter(List<FilterItem>  filters);
        void showMessage(String message);
        void dismiss();
        void stopLoading();
        void startLoading();
    }

    interface Model{
        void attachView (Presenter presenter, Context context);
        void getListFromServer(String filter);
        void getFilterList();
    }
}
