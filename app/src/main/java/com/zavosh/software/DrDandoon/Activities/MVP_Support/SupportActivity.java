package com.zavosh.software.DrDandoon.Activities.MVP_Support;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.CustomViews.MyButton;
import com.zavosh.software.DrDandoon.CustomViews.MyEditText;
import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyTextView;
import com.zavosh.software.DrDandoon.CustomViews.MyToast;
import com.zavosh.software.DrDandoon.R;

public class SupportActivity extends AppCompatActivity implements Contract_Support.View{

    private MyEditText etv_subject,etv_description;
    private MyButton btn_send,btn_call,retry;
    private MyImageView iv_back;
    private LoadingDots loaderDots;private LinearLayout myProgressBar;
    private MyTextView tv_message;
    private Presenter_Support presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        references();
        listeners();
    }



    private void references() {
        etv_subject = findViewById(R.id.etv_subject);
        etv_description = findViewById(R.id.etv_subject);
        btn_send = findViewById(R.id.btn_send);
        btn_call = findViewById(R.id.btn_call);
        iv_back = findViewById(R.id.iv_back);

        tv_message = findViewById(R.id.tv_message);
        myProgressBar = findViewById(R.id.myProgressBar);
        retry = findViewById(R.id.retry);
        loaderDots = findViewById(R.id.loaderDots);
        iv_back = findViewById(R.id.iv_back);
        presenter = new Presenter_Support();
        presenter.attachView(this,SupportActivity.this);

    }

    private void listeners() {
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callClicked(getIntent().getExtras().getString(Content.SUPPORT_PHONE));
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendClicked(etv_subject.text(),etv_description.text());
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(SupportActivity.this,message);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void goToCall(String phone) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
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
    public void setMessageToMonitor(String message) {
        loaderDots.setVisibility(View.INVISIBLE);
        retry.setVisibility(View.VISIBLE);
        tv_message.setText(message);
    }
}
