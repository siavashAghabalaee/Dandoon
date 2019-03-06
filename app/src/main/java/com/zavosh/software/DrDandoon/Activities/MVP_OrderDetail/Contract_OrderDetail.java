package com.zavosh.software.DrDandoon.Activities.MVP_OrderDetail;

import android.content.Context;

import com.zavosh.software.DrDandoon.Retrofit.OrderDetailRequest.OrderDetailResult;
import com.zavosh.software.DrDandoon.Retrofit.PostPurchase.PostPurchaseResult;


public interface Contract_OrderDetail {
    interface View{
        void setData(OrderDetailResult data);
        void loadMyProgressbar();
        void stopMyProgressbar();
        void showMessage(String message);
        void setMessageOnProgressbar(String message);
        void showDialogPatientInfo();
        void setPatientInfo(PostPurchaseResult result);
        void dismiss();
    }

    interface Presenter{
        void attachView(View view, Context context);
        void onCreate(String orderId);
        void retryClicked(String orderId);
        void buyClicked(String orderCode);

        //----------------------

        void setData(OrderDetailResult data);
        void showMessage(String message);
        void setMessageOnProgressbar(String message);
        void setPatientInfo(PostPurchaseResult result);
        void dismiss();

    }

    interface Model{
        void attachPresenter(Presenter presenter , Context  context);
        void getDataFromServer(String ordeId);
        void buyFromServer(String orderCode);

    }
}
