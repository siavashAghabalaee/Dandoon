package com.zavosh.software.DrDandoon.Activities.MVP_HomeDoctor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.DrDandoon.Activities.MVP_ChoosePatient.ChoosePatientActivity;
import com.zavosh.software.DrDandoon.Activities.MVP_ChooseRole.ChooseRole;
import com.zavosh.software.DrDandoon.Activities.MVP_MyPatientList.MyPatientListActivity;
import com.zavosh.software.DrDandoon.Adapters.MyPagerAdapter;
import com.zavosh.software.DrDandoon.CustomViews.MyButton;
import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyTextView;
import com.zavosh.software.DrDandoon.CustomViews.MyToast;
import com.zavosh.software.DrDandoon.Helper.FabricSender;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.R;

import java.util.List;

public class HomeDoctorActivity extends AppCompatActivity implements Contract_HomeDoctor.View{

    private MyPagerAdapter myPagerAdapter;
    private ViewPager viewPager;
    private LinearLayout myProgressBar;
    private Presenter_HomeDoctor presenter;
    private LoadingDots loaderDots;
    private MyTextView tv_message,tv_name,tv_phone;
    private MyButton btn_retry;
    private Handler handler;
    private Runnable runnable;
    private MyImageView iv_logout;
    private CardView cv_add,cv_aboutUs,cv_invite,cv_support,cv_history;
    private AlertDialog show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FabricSender.home();
        reference();
        listeners();
        //showMessage("doctor");
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
        cv_aboutUs = findViewById(R.id.cv_aboutUs);
        cv_invite = findViewById(R.id.cv_invite);
        cv_support = findViewById(R.id.cv_support);
        cv_history = findViewById(R.id.cv_history);
        iv_logout = findViewById(R.id.iv_logout);

        presenter = new Presenter_HomeDoctor();
        presenter.attachView(this,HomeDoctorActivity.this);

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
                Intent intent = new Intent(HomeDoctorActivity.this,ChoosePatientActivity.class);
                startActivity(intent);
            }
        });
        cv_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.aboutUsClicked();
            }
        });

        cv_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.inviteClicked();
            }
        });
        cv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.supportClicked();
            }
        });

        cv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDoctorActivity.this,MyPatientListActivity.class);
                startActivity(intent);
            }
        });

        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeDoctorActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View rootView = inflater.inflate(R.layout.dialog_logout, null);
                builder.setView(rootView);
                builder.create();
                show = builder.show();
                show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                MyButton yes = rootView.findViewById(R.id.btn_yes);
                MyButton no = rootView.findViewById(R.id.btn_no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FabricSender.logout();
                        PublicMethods.clearAllData();
                        Intent intent = new Intent(HomeDoctorActivity.this, ChooseRole.class);
                        try {
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                        }catch (Exception e){}

                        startActivity(intent);
                        show.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(HomeDoctorActivity.this,message);
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
        myPagerAdapter  = new MyPagerAdapter(HomeDoctorActivity.this,images);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(500);
        tv_name.setText(name);
        tv_phone.setText(phone);

        handler.postDelayed(runnable,5000);

    }
}
