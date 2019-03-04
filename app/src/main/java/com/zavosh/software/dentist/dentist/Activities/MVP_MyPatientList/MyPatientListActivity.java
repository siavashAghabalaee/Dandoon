package com.zavosh.software.dentist.dentist.Activities.MVP_MyPatientList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient.ChoosePatientActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_OrderDetail.OrderDetailActivity;
import com.zavosh.software.dentist.dentist.Adapters.FilterAdapter;
import com.zavosh.software.dentist.dentist.Adapters.MyPatientListAdapter;
import com.zavosh.software.dentist.dentist.Adapters.PatientAdapter;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyButton;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyProgressBarBlue;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.MyInterfaces.MyClickListener;
import com.zavosh.software.dentist.dentist.MyInterfaces.MyDialog;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest.MyPatientListResult;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientItemResult;

import java.util.ArrayList;
import java.util.List;

public class MyPatientListActivity extends AppCompatActivity implements Contract_MyPatientList.View,MyClickListener,MyDialog {
    private Presenter_MyPatientList presenter;
    private RecyclerView recyclerView;
    private MyImageView iv_filter,iv_back;
    private LinearLayoutManager layoutManager;
    private MyPatientListAdapter adapter;
    private List<MyPatientListResult> list;
    private String filter = "all";
    private ListView lv_filter;
    private AlertDialog show;
    private LinearLayout ll_progressBar;
    private FilterAdapter filterAdapter;
    private MyTextView tv_monitor;
    private MyProgressBarBlue loader;
    private List<MyPatientListResult> holderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient_list);
        reference();
        listener();
    }

    private void reference() {
        presenter = new Presenter_MyPatientList();
        presenter.attachView(this,MyPatientListActivity.this);
        iv_filter = findViewById(R.id.iv_filter);
        iv_back = findViewById(R.id.iv_back);
        recyclerView = findViewById(R.id.recyclerView);
        loader = findViewById(R.id.loader);
        tv_monitor = findViewById(R.id.tv_monitor);
        layoutManager = new LinearLayoutManager(MyPatientListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        adapter = new MyPatientListAdapter(list,MyPatientListActivity.this);
        adapter.myClickListener = this;
        adapter.myDialog = this;
        recyclerView.setAdapter(adapter);
    }

    private void listener(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume("all");
    }

    @Override
    public void setDataList(List<MyPatientListResult> patientList) {
        Log.i("kk",patientList.size()+"");
        holderList = patientList;
        if(patientList.size() == 0){
            //TODO text bede moredi yaft nashod
            tv_monitor.setText(R.string.notFind);
            list.clear();
            adapter.notifyDataSetChanged();
        }else {
            tv_monitor.setText("");
            list.clear();
            for (int i = 0; i < patientList.size(); i++) {
                list.add(patientList.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(MyPatientListActivity.this,message);
    }

    @Override
    public void stopLoading() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void startLoading() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void goDetailActivity(String id) {

    }

    @Override
    public void onMyClickListener(int position) {
        if (holderList != null){
            Intent intent = new Intent(MyPatientListActivity.this , OrderDetailActivity.class);
            intent.putExtra(Content.ORDER_KEY,holderList.get(position).getCode());
            startActivity(intent);
        }
    }

    @Override
    public void showDialog(String name, String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_patient_info, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout ll_box = rootView.findViewById(R.id.ll_box);
        LinearLayout ll_progressBar = rootView.findViewById(R.id.ll_progressBar);
        MyButton btn_close = rootView.findViewById(R.id.btn_close);
        MyButton btn_call = rootView.findViewById(R.id.btn_call);
        final MyTextView tv_phone = rootView.findViewById(R.id.tv_phone);
        MyTextView tv_name = rootView.findViewById(R.id.tv_name);


        ll_box.setVisibility(View.VISIBLE);
        ll_progressBar.setVisibility(View.GONE);

        tv_phone.setText(phone);
        tv_name.setText(name);

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
}
