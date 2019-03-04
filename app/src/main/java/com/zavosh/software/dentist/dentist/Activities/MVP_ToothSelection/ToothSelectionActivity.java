package com.zavosh.software.dentist.dentist.Activities.MVP_ToothSelection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.dentist.dentist.Activities.MVP_AnswerQuestions.AnswerQuestionsActivity;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyProgressBarBlue;
import com.zavosh.software.dentist.dentist.CustomViews.MyRadioButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.OrderRequest.OrderSender;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyCity;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.MyState;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import io.blackbox_vision.wheelview.view.WheelView;

public class ToothSelectionActivity extends AppCompatActivity implements Contract_ToothSelection.View {

    private Presenter_ToothSelection presenter;
    private WheelView toothNumber,stateAndCity;
    private MyButton btn_continue,confirmBtn,cancel_Btn;
    private CardView cv_chooseCity;
    private SegmentedGroup segment;
    private MyImageView iv_back;
    private LinearLayout ll_box;
    private MyProgressBarBlue loader;
    private MyTextView tv_titleDialog,tv_city;
    private int filter = 0;
    private AlertDialog show;
    private MyRadioButton buttonChild,buttonOld;
    private ImageView imagetooths;
    public static OrderSender orderSender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tooth_selection);
        reference();
        listeners();
    }

    private void reference() {
        orderSender = new OrderSender();
        presenter = new Presenter_ToothSelection();
        presenter.attachView(this,ToothSelectionActivity.this);
        btn_continue = findViewById(R.id.btn_continue);
        cv_chooseCity = findViewById(R.id.cv_chooseCity);
        segment = findViewById(R.id.segment);
        tv_city = findViewById(R.id.tv_city);
        imagetooths = findViewById(R.id.imagetooths);
        iv_back = findViewById(R.id.iv_back);
        buttonOld = findViewById(R.id.buttonOld);
        buttonChild = findViewById(R.id.buttonChild);





        List<String> toothList = new ArrayList<>();
        toothNumber = findViewById(R.id.toothNumber);
        String[] toothArray = getResources().getStringArray(R.array.toothArray);
        for (int i = 0; i < toothArray.length; i++) {
            toothList.add(toothArray[i]);
        }
        toothNumber.setItems(toothList);
    }

    private void listeners(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cv_chooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseCityClicked();
            }
        });
        buttonOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagetooths.setImageResource(R.drawable.tooths);
            }
        });
        buttonChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagetooths.setImageResource(R.drawable.mini);
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOld.isChecked()){
                    presenter.nextClicked(false,toothNumber.getSelectedItem());

                }else {
                    presenter.nextClicked(true,toothNumber.getSelectedItem());
                }
            }
        });


    };

    @Override
    public void showMessage(String message) {
        MyToast.showToast(ToothSelectionActivity.this,message);
    }

    @Override
    public void goNext(boolean ageType, int toothNumber , String cityId) {
        Intent intent = new Intent(ToothSelectionActivity.this,AnswerQuestionsActivity.class);
        //intent.putExtra(Content.AGY_TYP_KEY,ageType+"");
        //intent.putExtra(Content.TOOTH_NUM_KEY,toothNumber+"");
        //intent.putExtra(Content.CITY_KEY,cityId);
        Log.i("kk",cityId);

        startActivity(intent);
    }

    @Override
    public void showDialogChooseCity() {
        showDialogChooseCitySetting();
    }

    @Override
    public void setStateList(List<MyState> stateList) {

        ll_box.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        tv_titleDialog.setText(getString(R.string.chooseState));

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            stringList.add(stateList.get(i).getState());
        }
        stateAndCity.setItems(stringList);

        filter = 0;

    }

    @Override
    public void setCity(List<MyCity> cityList) {
        Log.i("kk","oomad");
        ll_box.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        tv_titleDialog.setText(getString(R.string.chooseCity));

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            stringList.add(cityList.get(i).getCity());
        }
        stateAndCity.setItems(stringList);

        filter = 1;
    }

    @Override
    public void loadDialog() {
        ll_box.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCityName(String cityName) {
        show.dismiss();
        tv_city.setText(cityName);
    }


    public void showDialogChooseCitySetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_choose_city, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ll_box = rootView.findViewById(R.id.ll_box);
        loader = rootView.findViewById(R.id.loader);
        stateAndCity = rootView.findViewById(R.id.stateAndCity);
        confirmBtn = rootView.findViewById(R.id.confirmBtn);
        cancel_Btn = rootView.findViewById(R.id.cancel_Btn);
        tv_titleDialog = rootView.findViewById(R.id.tv_titleDialog);
        stateAndCity.setTextDirection(Gravity.CENTER);

        ll_box.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);

        filter = 0;

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.acceptClicked(stateAndCity.getSelectedItem(),filter);
            }
        });
        cancel_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    };


}
