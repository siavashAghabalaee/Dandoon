package com.zavosh.software.dentist.dentist.Activities.MVP_OrderDetail;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zavosh.software.dentist.dentist.Activities.MVP_ImageZoom.ZoomImageActivity;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest.NewQuestion;
import com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest.OrderDetailResult;
import com.zavosh.software.dentist.dentist.Retrofit.PostPurchase.PostPurchaseResult;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity implements Contract_OrderDetail.View{

    private String orderCode;
    private Presenter_OrderDetail presenter;

    private MyTextView tv_message,doctorText,tv_city,tv_ageType,tv_toothNumber,tv_typeTitle;
    private MyTextView questionOne,questionTwo,questionThree,questionFour,questionFive,questionSix;
    private LinearLayout myProgressBar , ll_box ,ll_progressBar;
    private MyButton retry,btn_buy,btn_close,btn_call;

    private MyImageView image1,image2,image3,iv_opg,back;
    private AlertDialog show;
    private MyTextView tv_phone,tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        reference();
        listeners();

    }

    private void reference() {
        orderCode = getIntent().getExtras().getString(Content.ORDER_KEY);
        presenter = new Presenter_OrderDetail();
        presenter.attachView(this,OrderDetailActivity.this);
        myProgressBar = findViewById(R.id.myProgressBar);
        tv_message = findViewById(R.id.tv_message);
        retry = findViewById(R.id.retry);
        btn_buy = findViewById(R.id.btn_buy);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        back = findViewById(R.id.iv_back);
        iv_opg = findViewById(R.id.iv_opg);
        doctorText = findViewById(R.id.doctorText);
        tv_city = findViewById(R.id.tv_city);
        tv_ageType = findViewById(R.id.tv_ageType);
        tv_toothNumber = findViewById(R.id.tv_toothNumber);
        tv_typeTitle = findViewById(R.id.tv_typeTitle);
        questionOne = findViewById(R.id.questionOne);
        questionTwo = findViewById(R.id.questionTwo);
        questionThree = findViewById(R.id.questionThree);
        questionFour = findViewById(R.id.questionFour);
        questionFive = findViewById(R.id.questionFive);
        questionSix = findViewById(R.id.questionSix);
        presenter.onCreate(orderCode);
    }

    private void listeners(){
        final Intent intent = new Intent(OrderDetailActivity.this,ZoomImageActivity.class);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!image1.imageUrl.equals("null")) {
                    intent.putExtra(Content.IMAGE_KEY, image1.imageUrl);
                    startActivity(intent);
                }
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!image2.imageUrl.equals("null")) {
                    intent.putExtra(Content.IMAGE_KEY, image2.imageUrl);
                    startActivity(intent);
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!image3.imageUrl.equals("null")) {
                    intent.putExtra(Content.IMAGE_KEY, image3.imageUrl);
                    startActivity(intent);
                }
            }
        });

        iv_opg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!iv_opg.imageUrl.equals("null")) {
                    intent.putExtra(Content.IMAGE_KEY, iv_opg.imageUrl);
                    startActivity(intent);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.buyClicked(orderCode);
            }
        });
    }

    @Override
    public void setData(OrderDetailResult data) {
        doctorText.setText(data.getDoctorInfo());

        tv_city.setText(data.getCity());
        tv_ageType.setText(data.getKidStatus());
        tv_toothNumber.setText(data.getNumber());
        tv_typeTitle.setText(data.getTypeTitle());

        List<String> images = data.getImages();
        switch (images.size()){
            case 0:
                image1.setImageResource(R.mipmap.tooth);
                image2.setImageResource(R.mipmap.tooth);
                image3.setImageResource(R.mipmap.tooth);
                break;
            case 1:
                image1.setPicasso(images.get(0));
                image2.setImageResource(R.mipmap.tooth);
                image3.setImageResource(R.mipmap.tooth);
                break;
            case 2:
                image1.setPicasso(images.get(0));
                image2.setPicasso(images.get(1));
                image3.setImageResource(R.mipmap.tooth);
                break;
            case 3:
                image1.setPicasso(images.get(0));
                image2.setPicasso(images.get(1));
                image3.setPicasso(images.get(2));
                break;
        }

        if (data.getOpgImage() != null){
            iv_opg.setPicasso(data.getOpgImage());
        }else {
            iv_opg.setImageResource(R.mipmap.opg_icon);
        }

        //--------questions
        NewQuestion question1 = data.getQuestions().get(0);

        String ans1 = "";
        for (int i = 0; i < question1.getResponseId().size(); i++) {
            ans1 = ans1 + question1.getResponseId().get(i)+ "  ";
        }

        questionOne.setText(ans1);

        //---------------
        NewQuestion question2 = data.getQuestions().get(1);

        String ans2 = "";
        for (int i = 0; i < question2.getResponseId().size(); i++) {
            ans2 = ans2 + question2.getResponseId().get(i) + "  ";
        }

        questionTwo.setText(ans2);

        //---------------
        NewQuestion question3 = data.getQuestions().get(2);

        String ans3 = "";
        for (int i = 0; i < question3.getResponseId().size(); i++) {
            ans3 = ans3 + question3.getResponseId().get(i) + "  ";
        }

        questionThree.setText(ans3);

        //---------------
        NewQuestion question4 = data.getQuestions().get(3);

        String ans4 = "";
        for (int i = 0; i < question4.getResponseId().size(); i++) {
            ans4 = ans4 + question4.getResponseId().get(i) + "  ";
        }

        questionFour.setText(ans4);

        //---------------
        NewQuestion question5 = data.getQuestions().get(4);

        String ans5 = "";
        for (int i = 0; i < question5.getResponseId().size(); i++) {
            ans5 = ans5 + question5.getResponseId().get(i) + "  ";
        }

        questionFive.setText(ans5);

        //---------------
        NewQuestion question6 = data.getQuestions().get(5);

        String ans6 = "";
        for (int i = 0; i < question6.getResponseId().size(); i++) {
            ans6 = ans6 + question6.getResponseId().get(i) + "  ";
        }

        questionSix.setText(ans6);




    }

    @Override
    public void loadMyProgressbar() {
        myProgressBar.setVisibility(View.VISIBLE);
        tv_message.setText("");
        retry.setVisibility(View.GONE);
    }

    @Override
    public void stopMyProgressbar() {
        myProgressBar.setVisibility(View.GONE);
        tv_message.setText("");
        retry.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(OrderDetailActivity.this,message);
    }

    @Override
    public void setMessageOnProgressbar(String message) {
        myProgressBar.setVisibility(View.VISIBLE);
        tv_message.setText(message);
        retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDialogPatientInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_patient_info, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ll_box = rootView.findViewById(R.id.ll_box);
        ll_progressBar = rootView.findViewById(R.id.ll_progressBar);
        btn_close = rootView.findViewById(R.id.btn_close);
        btn_call = rootView.findViewById(R.id.btn_call);
        tv_phone = rootView.findViewById(R.id.tv_phone);
        tv_name = rootView.findViewById(R.id.tv_name);


        ll_box.setVisibility(View.GONE);
        ll_progressBar.setVisibility(View.VISIBLE);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tv_phone.getText().toString()));
                startActivity(intent);
            }
        });

    }

    @Override
    public void setPatientInfo(PostPurchaseResult result) {
        ll_box.setVisibility(View.VISIBLE);
        ll_progressBar.setVisibility(View.GONE);

        tv_name.setText(result.getFullName());
        tv_phone.setText(result.getCellNumber());
    }

    @Override
    public void dismiss() {
        show.dismiss();
    }
}
