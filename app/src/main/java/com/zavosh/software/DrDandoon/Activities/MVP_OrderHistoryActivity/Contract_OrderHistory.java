package com.zavosh.software.DrDandoon.Activities.MVP_OrderHistoryActivity;

import android.content.Context;

import com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest.PatientOrder;

import java.util.List;

public interface Contract_OrderHistory {
    interface View{
        void showMessage(String message);
        void startLoading();
        void stopLoading();
        void setMessageToMonitor(String message);
        void setData(List<PatientOrder> data);
    }

    interface Presenter{
        void attachView (View view, Context context);
        void onResume();
        void retryClicked();

        //-------------
        void setData(List<PatientOrder> data);
        void showMessage(String message);
        void startLoading();
        void stopLoading();
        void setMessageToMonitor(String message);
    }
    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void getList();

    }
}
