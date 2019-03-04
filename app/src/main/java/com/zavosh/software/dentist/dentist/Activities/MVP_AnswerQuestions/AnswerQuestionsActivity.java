package com.zavosh.software.dentist.dentist.Activities.MVP_AnswerQuestions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.zavosh.software.dentist.dentist.Activities.MVP_SelectImages.SelectImagesActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_ToothSelection.ToothSelectionActivity;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyCheckBox;
import com.zavosh.software.dentist.dentist.CustomViews.MyEditText;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.OrderRequest.Question;

import java.util.ArrayList;
import java.util.List;

public class AnswerQuestionsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private MyCheckBox ch_much , ch_low , ch_noPain , ch_medium;
    private MyCheckBox ch_known , ch_unknown ;
    private MyCheckBox ch_obscure , ch_sharpPain , ch_slappery , ch_constant, ch_nightly;
    private MyCheckBox ch_automatically , ch_cold , ch_heat , ch_chew;
    private MyCheckBox ch_no , ch_restoration , ch_rootCanal ;
    private MyImageView back;
    private MyButton btn_continue;
    private MyEditText etv_drugs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);
        reference();
        listeners();
    }

    private void reference() {
        //question 1---------------------------
        ch_low = findViewById(R.id.ch_low);
        ch_medium = findViewById(R.id.ch_medium);
        ch_noPain = findViewById(R.id.ch_NoPain);
        ch_much = findViewById(R.id.ch_much);
        etv_drugs = findViewById(R.id.etv_drugs);

        ch_low.setOnCheckedChangeListener(this);
        ch_medium.setOnCheckedChangeListener(this);
        ch_noPain.setOnCheckedChangeListener(this);
        ch_much.setOnCheckedChangeListener(this);

        //question 2---------------------------
        ch_known = findViewById(R.id.ch_known);
        ch_unknown = findViewById(R.id.ch_unknown);

        ch_known.setOnCheckedChangeListener(this);
        ch_unknown.setOnCheckedChangeListener(this);


        //question 3---------------------------
        ch_obscure = findViewById(R.id.ch_obscure);
        ch_sharpPain = findViewById(R.id.ch_sharpPain);
        ch_slappery = findViewById(R.id.ch_slappery);
        ch_constant = findViewById(R.id.ch_constant);
        ch_nightly = findViewById(R.id.ch_nightly);

        //question 4---------------------------
        ch_automatically = findViewById(R.id.ch_automatically);
        ch_cold = findViewById(R.id.ch_cold);
        ch_heat = findViewById(R.id.ch_heat);
        ch_chew = findViewById(R.id.ch_chew);

        //question 5---------------------------

        ch_no = findViewById(R.id.ch_no);
        ch_restoration = findViewById(R.id.ch_restoration);
        ch_rootCanal = findViewById(R.id.ch_rootCanal);

        ch_no.setOnCheckedChangeListener(this);
        ch_restoration.setOnCheckedChangeListener(this);
        ch_rootCanal.setOnCheckedChangeListener(this);

        back = findViewById(R.id.iv_back);
        btn_continue = findViewById(R.id.btn_continue);
    }

    private void listeners(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAns1()){
                    if (isAns2()){
                        if (isAns3()){
                            if (isAns4()){
                                if (isAns5()){



                                    putData();



                                }else {
                                    MyToast.showToast(AnswerQuestionsActivity.this,getString(R.string.ans5));
                                }
                            }else {
                                MyToast.showToast(AnswerQuestionsActivity.this,getString(R.string.ans4));
                            }
                        }else {
                            MyToast.showToast(AnswerQuestionsActivity.this,getString(R.string.ans3));
                        }
                    }else {
                        MyToast.showToast(AnswerQuestionsActivity.this,getString(R.string.ans2));
                    }
                }else {
                    MyToast.showToast(AnswerQuestionsActivity.this,getString(R.string.ans1));
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.ch_low :
                if (isChecked){
                    ch_medium.setChecked(false);
                    ch_noPain.setChecked(false);
                    ch_much.setChecked(false);
                }
                break;
            case R.id.ch_medium :
                if (isChecked){
                    ch_low.setChecked(false);
                    ch_noPain.setChecked(false);
                    ch_much.setChecked(false);
                }
                break;
            case R.id.ch_NoPain :
                if (isChecked){
                    ch_low.setChecked(false);
                    ch_medium.setChecked(false);
                    ch_much.setChecked(false);
                }
                break;
            case R.id.ch_much :
                if (isChecked){
                    ch_low.setChecked(false);
                    ch_medium.setChecked(false);
                    ch_noPain.setChecked(false);
                }
                break;

            //------------------------------------2
            case R.id.ch_known :
                if (isChecked){
                    ch_unknown.setChecked(false);
                }
                break;
            case R.id.ch_unknown :
                if (isChecked){
                    ch_known.setChecked(false);
                }
                break;



            //------------------------------------5

            case R.id.ch_no :
                if (isChecked){
                    ch_restoration.setChecked(false);
                    ch_rootCanal.setChecked(false);
                }
                break;
            case R.id.ch_restoration :
                if (isChecked){
                    ch_no.setChecked(false);
                }
                break;

            case R.id.ch_rootCanal :
                if (isChecked){
                    ch_no.setChecked(false);
                }
                break;
        }
    }

    public boolean isAns1(){
        if (ch_much.isChecked()){
            return true;
        }else if(ch_low.isChecked()){
            return true;
        }else if (ch_noPain.isChecked()){
            return true;
        }else if (ch_medium.isChecked()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isAns2(){
        if (ch_known.isChecked()){
            return true;
        }else if(ch_unknown.isChecked()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isAns3(){
        boolean b = false;
        if (ch_obscure.isChecked()){
            b =  true;
        }
        if(ch_sharpPain.isChecked()){
            b = true;
        }
        if(ch_slappery.isChecked()){
            b = true;
        }
        if(ch_constant.isChecked()){
            b = true;
        }
        if(ch_nightly.isChecked()){
            b = true;
        }

        return b;
    }

    public boolean isAns4(){
        boolean b = false;
        if (ch_automatically.isChecked()){
            b =  true;
        }
        if(ch_cold.isChecked()){
            b = true;
        }
        if(ch_heat.isChecked()){
            b = true;
        }
        if(ch_chew.isChecked()){
            b = true;
        }

        return b;
    }

    public boolean isAns5(){
        boolean b = false;
        if (ch_no.isChecked()){
            b =  true;
        }
        if(ch_restoration.isChecked()){
            b = true;
        }
        if(ch_rootCanal.isChecked()){
            b = true;
        }

        return b;
    }

    private void putData() {

        //---------------question 1--------------
        List<Question> questions = new ArrayList<>();

        Question question1 = new Question();
        question1.setQuestionId(1);

        List<String> responseList1 = new ArrayList<>();
        if (ch_noPain.isChecked()){
            responseList1.add(ch_noPain.getText().toString());
        }
        if (ch_low.isChecked()){
            responseList1.add(ch_low.getText().toString());
        }
        if (ch_medium.isChecked()){
            responseList1.add(ch_medium.getText().toString());
        }
        if (ch_much.isChecked()){
            responseList1.add(ch_much.getText().toString());
        }

        question1.setResponseId(responseList1);

        questions.add(question1);




        //---------------question 2--------------

        Question question2 = new Question();
        question2.setQuestionId(2);

        List<String> responseList2 = new ArrayList<>();
        if (ch_known.isChecked()){
            responseList2.add(ch_known.getText().toString());
        }
        if (ch_unknown.isChecked()){
            responseList2.add(ch_unknown.getText().toString());
        }

        question2.setResponseId(responseList2);

        questions.add(question2);



        //---------------question 3--------------
        Question question3 = new Question();
        question3.setQuestionId(3);

        List<String> responseList3 = new ArrayList<>();
        if (ch_obscure.isChecked()){
            responseList3.add(ch_obscure.getText().toString());
        }
        if (ch_sharpPain.isChecked()){
            responseList3.add(ch_sharpPain.getText().toString());
        }
        if (ch_slappery.isChecked()){
            responseList3.add(ch_slappery.getText().toString());
        }
        if (ch_constant.isChecked()){
            responseList3.add(ch_constant.getText().toString());
        }
        if (ch_nightly.isChecked()){
            responseList3.add(ch_nightly.getText().toString());
        }

        question3.setResponseId(responseList3);

        questions.add(question3);


        //---------------question 4--------------
        Question question4 = new Question();
        question4.setQuestionId(4);

        List<String> responseList4 = new ArrayList<>();
        if (ch_automatically.isChecked()){
            responseList4.add(ch_automatically.getText().toString());
        }
        if (ch_heat.isChecked()){
            responseList4.add(ch_heat.getText().toString());
        }
        if (ch_cold.isChecked()){
            responseList4.add(ch_cold.getText().toString());
        }
        if (ch_chew.isChecked()){
            responseList4.add(ch_chew.getText().toString());
        }

        question4.setResponseId(responseList4);

        questions.add(question4);



        //---------------question 5--------------
        Question question5 = new Question();
        question5.setQuestionId(5);

        List<String> responseList5 = new ArrayList<>();
        if (ch_no.isChecked()){
            responseList5.add(ch_no.getText().toString());
        }
        if (ch_restoration.isChecked()){
            responseList5.add(ch_restoration.getText().toString());
        }
        if (ch_rootCanal.isChecked()){
            responseList5.add(ch_rootCanal.getText().toString());
        }

        question5.setResponseId(responseList5);

        questions.add(question5);


        //---------------question 5--------------
        Question question6 = new Question();
        question6.setQuestionId(6);

        List<String> responseList6 = new ArrayList<>();
        responseList6.add(etv_drugs.text());

        question6.setResponseId(responseList6);

        questions.add(question6);


        ToothSelectionActivity.orderSender.setQuestions(questions);


        startActivity(new Intent(AnswerQuestionsActivity.this,SelectImagesActivity.class));
    }

}
