package com.zavosh.software.dentist.dentist.Activities.MVP_ForgotPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyEditText;
import com.zavosh.software.dentist.dentist.CustomViews.MyProgressBarWhite;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.R;

public class ForgotPassActivity extends AppCompatActivity implements Contract_ForgotPass.View{

    private LinearLayout back;
    private MyButton btn_send;
    private MyEditText ev_phone;
    private Presenter_Forgot presenter;
    private MyProgressBarWhite loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        reference();
        listeners();
    }

    private void reference() {
        back = findViewById(R.id.back);
        btn_send = findViewById(R.id.btn_send);
        ev_phone = findViewById(R.id.ev_phone);
        loader = findViewById(R.id.loader);
        presenter = new Presenter_Forgot();
        presenter.attachView(this,ForgotPassActivity.this);
    }

    private void listeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backClicked();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra(Content.ROLE_KEY)){
                    presenter.sendClicked(ev_phone.text(),getIntent().getExtras().getString(Content.ROLE_KEY));
                }

            }
        });
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(ForgotPassActivity.this,message);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void freeze() {
        btn_send.setClickable(false);
    }

    @Override
    public void unFreeze() {
        btn_send.setClickable(true);
    }

    @Override
    public void startLoadBtn() {
        btn_send.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadBtn() {
        btn_send.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        PublicMethods.hideKeyboard(ForgotPassActivity.this);

    }
}
