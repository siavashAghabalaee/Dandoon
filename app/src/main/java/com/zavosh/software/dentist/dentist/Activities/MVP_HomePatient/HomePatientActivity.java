package com.zavosh.software.dentist.dentist.Activities.MVP_HomePatient;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.dentist.dentist.Activities.MVP_ToothSelection.ToothSelectionActivity;
import com.zavosh.software.dentist.dentist.Adapters.MyPagerAdapter;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.R;

import java.util.List;



public class HomePatientActivity extends AppCompatActivity implements Contract_HomePatient.View{

    private MyPagerAdapter myPagerAdapter;
    private ViewPager viewPager;
    private LinearLayout myProgressBar;
    private Presenter_HomePatient presenter;
    private LoadingDots loaderDots;
    private MyTextView tv_message,tv_name,tv_phone;
    private MyButton btn_retry;
    private Handler handler;
    private Runnable runnable;
    private CardView cv_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);

        reference();
        listeners();
        showMessage("patient");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    public void reference(){

        viewPager = findViewById(R.id.viewPager);
        myProgressBar = findViewById(R.id.myProgressBar);
        loaderDots = findViewById(R.id.loaderDots);
        tv_message = findViewById(R.id.tv_message);
        btn_retry = findViewById(R.id.retry);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        cv_add = findViewById(R.id.cv_add);

        presenter = new Presenter_HomePatient();
        presenter.attachView(this,HomePatientActivity.this);

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                currentItem++;
                viewPager.setCurrentItem(currentItem);
                handler.postDelayed(runnable,5000);
                Log.i("courentImage", viewPager.getCurrentItem() +"");
            }
        };
    }

    public void listeners(){
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.retryClicked();
            }
        });
        cv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePatientActivity.this,ToothSelectionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(HomePatientActivity.this,message);
    }

    @Override
    public void stopSlider() {
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void showProgressBar() {
        myProgressBar.setVisibility(View.VISIBLE);
        loaderDots.setVisibility(View.VISIBLE);
        btn_retry.setVisibility(View.INVISIBLE);
        tv_message.setText("");
    }

    @Override
    public void hideProgressBarr() {
        myProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMessageInMonitor(String message) {
        //ham loader vase ham message namayesh dade beshe
        loaderDots.setVisibility(View.INVISIBLE);
        btn_retry.setVisibility(View.VISIBLE);
        tv_message.setText(message);
    }

    @Override
    public void setData(List<String> images, String phone, String name, String imageProfile) {
        myPagerAdapter  = new MyPagerAdapter(HomePatientActivity.this,images);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(500);
        tv_name.setText(name);
        tv_phone.setText(phone);

        handler.postDelayed(runnable,5000);

    }
}