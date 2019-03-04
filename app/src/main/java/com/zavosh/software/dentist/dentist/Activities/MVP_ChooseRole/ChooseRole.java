package com.zavosh.software.dentist.dentist.Activities.MVP_ChooseRole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.Activities.MVP_Login.LoginActivity;
import com.zavosh.software.dentist.dentist.R;

public class ChooseRole extends AppCompatActivity implements Contract_ChooseRole.View{

    private MyButton btn_doctor,btn_sick;
    private Presenter_ChooseRole presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        reference();
        listener();
    }

    private void reference() {
        btn_doctor = findViewById(R.id.btn_doctor);
        btn_sick = findViewById(R.id.btn_sick);
        presenter = new Presenter_ChooseRole();
        presenter.attachView(this,ChooseRole.this);
    }

    private void listener(){
        btn_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doctorClicked();
            }
        });
        btn_sick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sickClicked();
            }
        });
    }

    @Override
    public void goNextActivity(String role) {
        Intent intent = new Intent(ChooseRole.this , LoginActivity.class);
        intent.putExtra(Content.ROLE_KEY,role);
        startActivity(intent);
    }
}
