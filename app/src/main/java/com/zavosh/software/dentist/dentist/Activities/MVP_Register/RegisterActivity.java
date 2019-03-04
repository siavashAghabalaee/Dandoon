package com.zavosh.software.dentist.dentist.Activities.MVP_Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zavosh.software.dentist.dentist.Activities.MVP_Activation.ActivationActivity;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyEditText;
import com.zavosh.software.dentist.dentist.CustomViews.MyProgressBarWhite;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.R;

public class RegisterActivity extends AppCompatActivity implements Contract_Register.View{

    private MyButton btn_register;
    private LinearLayout back,btn_login;
    private MyEditText ev_fullName,ev_phone;
    private Presenter_Register presenter;
    private String role = "";
    private MyProgressBarWhite loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reference();
        listeners();
    }

    private void reference() {
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        back = findViewById(R.id.back);
        ev_fullName = findViewById(R.id.ev_fullName);
        ev_phone = findViewById(R.id.ev_phone);
        loader = findViewById(R.id.loader);
        presenter = new Presenter_Register();
        presenter.attachView(this,RegisterActivity.this);

        if (getIntent().hasExtra(Content.ROLE_KEY)){
            role = getIntent().getExtras().getString(Content.ROLE_KEY);
        }
    }

    private void listeners() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerClicked(ev_fullName.text(),ev_phone.text(),role);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goLoginClicked();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backClicked();
            }
        });
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(RegisterActivity.this,message);
    }

    @Override
    public void goActivationPage(String role) {
        startActivity(new Intent(RegisterActivity.this,ActivationActivity.class).putExtra(Content.ROLE_KEY,role));
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void startLoadBtn() {
        btn_register.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadBtn() {
        btn_register.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        PublicMethods.hideKeyboard(RegisterActivity.this);
    }
}
