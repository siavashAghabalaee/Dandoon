package com.zavosh.software.dentist.dentist.Activities.MVP_ToothSelection;

import android.content.Context;

import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyCity;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyState;

import java.util.List;

public interface Contract_ToothSelection {
    interface View{
        void showMessage(String message);
        void goNext(boolean ageType ,int toothNumber , String cityId);
        void showDialogChooseCity();
        void setStateList(List<MyState> stateList);
        void setCity(List<MyCity> cityList);
        void loadDialog();
        void setCityName(String cityName);

    }

    interface Presenter{
        void attachView(View view , Context context);
        void nextClicked(boolean ageType ,int toothNumber);
        void chooseCityClicked();
        void acceptClicked(int stateOrCity , int filter);
        //--------------------
        void showMessage(String message);
        void valueIsOk(boolean ageType ,int toothNumber , String cityId);
        void setState(List<MyState> stateList);
        void setCity(List<MyCity> cityList);
        void loadDialog();
        void setCityName(String cityName);

    }

    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void checkValue(boolean ageType ,int toothNumber);
        void getState();
        void acceptClicked(int stateOrCity , int filter);


    }
}
