package com.zavosh.software.dentist.dentist.Activities.MVP_ChoosePatient;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.zavosh.software.dentist.dentist.Activities.MVP_HomeDoctor.HomeDoctorActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_MyPatientList.MyPatientListActivity;
import com.zavosh.software.dentist.dentist.Activities.MVP_OrderDetail.OrderDetailActivity;
import com.zavosh.software.dentist.dentist.Adapters.FilterAdapter;
import com.zavosh.software.dentist.dentist.Adapters.PatientAdapter;
import com.zavosh.software.dentist.dentist.Content.Content;
import com.zavosh.software.dentist.dentist.CustomViews.MyImageView;
import com.zavosh.software.dentist.dentist.CustomViews.MyProgressBarBlue;
import com.zavosh.software.dentist.dentist.CustomViews.MyTextView;
import com.zavosh.software.dentist.dentist.CustomViews.MyToast;
import com.zavosh.software.dentist.dentist.MyInterfaces.MyClickListener;
import com.zavosh.software.dentist.dentist.R;
import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItem;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientItemResult;

import java.util.ArrayList;
import java.util.List;

public class ChoosePatientActivity extends AppCompatActivity implements Contract_ChoosePatient.View, MyClickListener {

    private Presenter_ChoosePatient presenter;
    private RecyclerView recyclerView;
    private MyImageView iv_filter;
    private LinearLayoutManager layoutManager;
    private PatientAdapter adapter;
    private List<PatientItemResult> list;
    private String filter = "all";
    private ListView lv_filter;
    private AlertDialog show;
    private LinearLayout ll_progressBar;
    private FilterAdapter filterAdapter;
    private MyTextView tv_monitor;
    private MyProgressBarBlue loader;
    private FloatingActionButton menu_home;
    private FloatingActionButton menu_myPatient;
    private List<PatientItemResult> holderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_patient);
        reference();
        listeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(filter);
    }

    private void reference() {
        presenter = new Presenter_ChoosePatient();
        presenter.attachView(this,ChoosePatientActivity.this);
        iv_filter = findViewById(R.id.iv_filter);
        recyclerView = findViewById(R.id.recyclerView);
        loader = findViewById(R.id.loader);
        menu_home = findViewById(R.id.menu_home);
        menu_myPatient = findViewById(R.id.menu_list);
        tv_monitor = findViewById(R.id.tv_monitor);
        layoutManager = new LinearLayoutManager(ChoosePatientActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        adapter = new PatientAdapter(list,ChoosePatientActivity.this);
        adapter.myClickListener = this;
        recyclerView.setAdapter(adapter);
    }

    private void listeners() {
        iv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.filterClicked();
            }
        });
        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePatientActivity.this,HomeDoctorActivity.class);
                startActivity(intent);
            }
        });
        menu_myPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePatientActivity.this,MyPatientListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setDataList(List<PatientItemResult> patientList) {
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
    public void showDialogFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_filter, null);
        builder.setView(rootView);
        builder.create();
        show = builder.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        lv_filter = rootView.findViewById(R.id.lv_filter);
        ll_progressBar = rootView.findViewById(R.id.ll_progressBar);



    }

    @Override
    public void setFilterList(final List<FilterItem> filters) {
        ll_progressBar.setVisibility(View.GONE);
        filterAdapter = new FilterAdapter(ChoosePatientActivity.this,filters);
        lv_filter.setAdapter(filterAdapter);
        Log.i("kk",filters.size()+"");


        lv_filter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String typeId = filters.get(position).getId();
                presenter.itemFilterClicked(typeId);

            }
        });
    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(ChoosePatientActivity.this,message);
    }

    @Override
    public void dismiss() {
        show.dismiss();
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
        startActivity(new Intent(ChoosePatientActivity.this,OrderDetailActivity.class).putExtra(Content.ORDER_KEY,id));
    }

    @Override
    public void onMyClickListener(int position) {
        if (holderList != null){
            presenter.itemClicked(holderList.get(position).getCode());
        }
    }
}
