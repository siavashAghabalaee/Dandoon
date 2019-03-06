package com.zavosh.software.DrDandoon.Activities.MVP_Activation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zavosh.software.DrDandoon.Activities.MVP_ChoosePatient.ChoosePatientActivity;
import com.zavosh.software.DrDandoon.Activities.MVP_HomePatient.HomePatientActivity;
import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.CustomViews.MyButton;
import com.zavosh.software.DrDandoon.CustomViews.MyEditText;
import com.zavosh.software.DrDandoon.CustomViews.MyProgressBarWhite;
import com.zavosh.software.DrDandoon.CustomViews.MyToast;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.R;

public class ActivationActivity extends AppCompatActivity implements Contract_Activation.View{
    private MyButton btn_activation;
    private LinearLayout back;
    private MyEditText ev_code;
    private Presenter_Activation presenter;
    private MyProgressBarWhite loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        reference();
        listeners();
    }

    private void reference() {
        btn_activation = findViewById(R.id.btn_activation);
        loader = findViewById(R.id.loader);
        back = findViewById(R.id.back);
        ev_code = findViewById(R.id.ev_code);
        presenter = new Presenter_Activation();
        presenter.attachView(this,ActivationActivity.this);
    }

    private void listeners() {
        btn_activation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendClicked(ev_code.text());
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
        MyToast.showToast(ActivationActivity.this,message);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void startLoadBtn() {
        btn_activation.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadBtn() {
        btn_activation.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        PublicMethods.hideKeyboard(ActivationActivity.this);
    }


    @Override
    public void goHomePage() {
        Intent intent = null;
        String role = getIntent().getExtras().getString(Content.ROLE_KEY);
        if (role.equals(Content.DOCTOR)){
            intent = new Intent(ActivationActivity.this, ChoosePatientActivity .class);
        }else {
            intent = new Intent(ActivationActivity.this, HomePatientActivity.class);
        }
        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){}

        startActivity(intent);

    }


}
//https://www.getpostman.com/collections/7641d6dd606cc8b8f2db