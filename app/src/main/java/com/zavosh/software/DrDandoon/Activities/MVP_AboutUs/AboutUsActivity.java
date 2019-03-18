package com.zavosh.software.DrDandoon.Activities.MVP_AboutUs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.CustomViews.MyButton;
import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyTextView;
import com.zavosh.software.DrDandoon.CustomViews.MyToast;
import com.zavosh.software.DrDandoon.R;

public class AboutUsActivity extends AppCompatActivity implements Contract_AboutUs.View{

    private MyTextView tv_aboutUs,tv_message;
    private MyImageView iv_back;
    private LinearLayout myProgressBar;
    private MyButton retry;
    private LoadingDots loaderDots;
    private Presenter_AboutUs presenter;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        references();
        listeners();
    }

    private void references() {
        tv_aboutUs = findViewById(R.id.tv_aboutUs);
        iv_back = findViewById(R.id.iv_back);
        tv_message = findViewById(R.id.tv_message);
        myProgressBar = findViewById(R.id.myProgressBar);
        retry = findViewById(R.id.retry);
        loaderDots = findViewById(R.id.loaderDots);
        tv_aboutUs.setMovementMethod(new ScrollingMovementMethod());
        presenter = new Presenter_AboutUs();
        presenter.attachView(this,AboutUsActivity.this);
        presenter.onCreate();
        text = getIntent().getExtras().getString(Content.ABOUT_KEY);
        tv_aboutUs.setText(text);
    }

    private void listeners(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(AboutUsActivity.this,message);
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public void startLoading() {
        myProgressBar.setVisibility(View.VISIBLE);
        loaderDots.setVisibility(View.VISIBLE);
        retry.setVisibility(View.INVISIBLE);
        tv_message.setText("");
    }

    @Override
    public void stopLoading() {
        myProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMessageMonitor(String message) {
        loaderDots.setVisibility(View.INVISIBLE);
        retry.setVisibility(View.VISIBLE);
        tv_message.setText(message);
    }
}
