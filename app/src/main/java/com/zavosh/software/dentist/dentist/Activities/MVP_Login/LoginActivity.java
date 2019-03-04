package com.zavosh.software.dentist.dentist.Activities.MVP_Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient.ChoosePatientActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_HomeDoctor.HomeDoctorActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_HomePatient.HomePatientActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_ForgotPassword.ForgotPassActivity;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyEditText;
import com.zavosh.software.dentist.dentist.CustomViews.MyProgressBarWhite;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.Activities.MVP_Register.RegisterActivity;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.R;

public class LoginActivity extends AppCompatActivity implements Contract_Login.View {

    private LinearLayout forgot,back;
    private MyButton btn_login;
    private MyTextView btn_register;
    private Presenter_Login presenter;
    private MyEditText ev_phone,ev_password;
    private MyProgressBarWhite loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reference();
        listeners();
    }

    private void reference() {
        forgot = findViewById(R.id.forgot);
        back = findViewById(R.id.back);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        presenter = new Presenter_Login();
        ev_password = findViewById(R.id.ev_password);
        loader = findViewById(R.id.loader);
        ev_phone = findViewById(R.id.ev_phone);
        presenter.attachView(this,LoginActivity.this);

    }

    private void listeners() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerClicked();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.forgotPasswordClicked();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra(Content.ROLE_KEY)){
                    presenter.loginClicked(ev_phone.text(),ev_password.text(),getIntent().getExtras().getString(Content.ROLE_KEY));
                }

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
    public void goHomeDoctor() {
        Intent intent = new Intent(LoginActivity.this, ChoosePatientActivity.class);
        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){}

        startActivity(intent);
    }

    @Override
    public void goHomeSick() {
        Intent intent = new Intent(LoginActivity.this, HomePatientActivity.class);
        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){}

        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(LoginActivity.this,message);
    }

    @Override
    public void goForgotPassword() {
        if (getIntent().hasExtra(Content.ROLE_KEY)) {
            Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
            intent.putExtra(Content.ROLE_KEY, getIntent().getExtras().getString(Content.ROLE_KEY));
            startActivity(intent);
        }
    }

    @Override
    public void goRegisterActivity() {

        if (getIntent().hasExtra(Content.ROLE_KEY)){
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            intent.putExtra(Content.ROLE_KEY,getIntent().getExtras().getString(Content.ROLE_KEY));
            startActivity(intent);
        }

    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void startLoadBtn() {
        btn_login.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadBtn() {
        btn_login.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        PublicMethods.hideKeyboard(LoginActivity.this);
    }
}
