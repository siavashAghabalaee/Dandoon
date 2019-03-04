package com.zavosh.software.dentist.dentist.Activities.MVP_MyPatientList;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItem;
import com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest.MyPatientListResult;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientItemResult;

import java.util.List;

public interface Contract_MyPatientList {
    interface View {
        void setDataList(List<MyPatientListResult> patientList);
        void showMessage(String message);
        void stopLoading();
        void startLoading();
        void goDetailActivity(String id);

    }

    interface Presenter{
        void attachView (View view, Context context);
        void onResume(String filter);
        //--------------------
        void setDataList(List<MyPatientListResult> patientList);
        void showMessage(String message);
        void stopLoading();
        void startLoading();
    }

    interface Model{
        void attachView (Presenter presenter, Context context);
        void getListFromServer(String filter);
    }
}
