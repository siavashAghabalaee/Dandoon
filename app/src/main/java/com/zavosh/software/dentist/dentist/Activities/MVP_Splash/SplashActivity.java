package com.zavosh.software.dentist.dentist.Activities.MVP_Splash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient.ChoosePatientActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_ChooseRole.ChooseRole;
import com.zavosh.software.dentist.dentist.Activities.MVP_HomeDoctor.HomeDoctorActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_HomePatient.HomePatientActivity;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.Helper.PublicMethods;
import com.zavosh.software.dentist.dentist.R;

import java.nio.channels.spi.SelectorProvider;

public class SplashActivity extends AppCompatActivity implements Contract_Splash.View{
    private Presenter_Splash presenter;
    private LoadingDots loaderDots;
    private AlertDialog show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        reference();
        listeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void reference() {
        presenter = new Presenter_Splash();
        presenter.attachView(this,SplashActivity.this);
        loaderDots = findViewById(R.id.loaderDots);
    }

    private void listeners() {

    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(SplashActivity.this,message);
    }

    @Override
    public void showRetry() {
        showDialogRetry();
    }

    @Override
    public void showUpdate(String link , boolean isNecessary) {
        showDialogNewVersion(link,isNecessary);
    }

    @Override
    public void goHomeSick() {
        startActivity(new Intent(SplashActivity.this,HomePatientActivity.class));
        finish();
    }

    @Override
    public void goHomeDoctor() {
        startActivity(new Intent(SplashActivity.this,ChoosePatientActivity.class));
        finish();
    }

    @Override
    public void goChooseRole() {
        startActivity(new Intent(SplashActivity.this,ChooseRole.class));
        finish();
    }

    @Override
    public void hideLoader() {
        loaderDots.setVisibility(View.GONE);
    }

    @Override
    public void showLoader() {
        loaderDots.setVisibility(View.VISIBLE);
    }

    public void showDialogNewVersion(final String link, final boolean necessary) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_confirmation, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        MyButton yesBtn = rootView.findViewById(R.id.yesBtn);
        MyTextView textView = rootView.findViewById(R.id.text);
        MyButton noBtn = rootView.findViewById(R.id.noBtn);
        textView.setText(R.string.newVersion);
        yesBtn.setText(R.string.install);

        if (necessary) {
            noBtn.setText(R.string.exit);
        } else {
            noBtn.setText(R.string.later);
        }

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                //i.setData(Uri.parse(link));
                startActivity(i);
                finish();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
                if (necessary) {
                    onBackPressed();
                } else {
                    if (PublicMethods.loadData(PublicMethods.ROLE,"").equals(Content.DOCTOR)){
                        goHomeDoctor();
                    }else if (PublicMethods.loadData(PublicMethods.ROLE,"").equals(Content.SICK)){
                        goHomeSick();
                    }else {
                        goChooseRole();
                    }
                }
            }
        });
    }

    private void showDialogRetry(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_confirmation, null);
        builder.setView(rootView);
        builder.create();
        final AlertDialog show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        show.setCancelable(false);
        MyButton yesBtn = rootView.findViewById(R.id.yesBtn);
        MyButton noBtn = rootView.findViewById(R.id.noBtn);
        MyTextView textView = rootView.findViewById(R.id.text);
        //MyButton noBtn = rootView.findViewById(R.id.noBtn);
        textView.setText(R.string.checkConnection);
        yesBtn.setText(R.string.retry);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
                presenter.retryClicked();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
            }
        });
    }
}
