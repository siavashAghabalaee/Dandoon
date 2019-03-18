package com.zavosh.software.DrDandoon.Activities.MVP_OrderHistoryActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;
import com.zavosh.software.DrDandoon.Activities.MVP_OrderDetail.OrderDetailActivity;
import com.zavosh.software.DrDandoon.Adapters.PatientOrderAdapter;
import com.zavosh.software.DrDandoon.Content.Content;
import com.zavosh.software.DrDandoon.CustomViews.MyButton;
import com.zavosh.software.DrDandoon.CustomViews.MyImageView;
import com.zavosh.software.DrDandoon.CustomViews.MyTextView;
import com.zavosh.software.DrDandoon.CustomViews.MyToast;
import com.zavosh.software.DrDandoon.MyInterfaces.MyClickListener;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest.PatientOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements Contract_OrderHistory.View,MyClickListener {

    private RecyclerView recyclerView;
    private MyTextView tv_message; private LoadingDots loaderDots; private LinearLayout myProgressBar; MyButton retry;
    private MyImageView iv_Back;

    private LinearLayoutManager layoutManager;
    private List<PatientOrder> orderList;
    private PatientOrderAdapter adapter;

    private Presenter_OrderHistory presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        references();
        listeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void references(){
        presenter = new Presenter_OrderHistory();
        presenter.attachView(this,OrderHistoryActivity.this);
        tv_message = findViewById(R.id.tv_message);
        myProgressBar = findViewById(R.id.myProgressBar);
        retry = findViewById(R.id.retry);
        loaderDots = findViewById(R.id.loaderDots);
        iv_Back = findViewById(R.id.iv_back);
        recyclerView = findViewById(R.id.recyclerView);
        orderList = new ArrayList<>();
        adapter = new PatientOrderAdapter(orderList,OrderHistoryActivity.this);
        layoutManager = new LinearLayoutManager(OrderHistoryActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.myClickListener = this;
    }

    public void listeners(){
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.retryClicked();
            }
        });

    }

    @Override
    public void showMessage(String message) {
        MyToast.showToast(OrderHistoryActivity.this,message);
    }

    @Override
    public void startLoading() {
        myProgressBar.setVisibility(View.VISIBLE);
        loaderDots.setVisibility(View.VISIBLE);
        retry.setVisibility(View.INVISIBLE);
        tv_message.setText("");
    }

    @Override
    public void stopLoading() {
        myProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMessageToMonitor(String message) {
        loaderDots.setVisibility(View.INVISIBLE);
        retry.setVisibility(View.VISIBLE);
        tv_message.setText(message);
    }

    @Override
    public void setData(List<PatientOrder> data) {
        Log.i("kk",data.size()+"");
        orderList.clear();
        for (int i = 0; i < data.size(); i++) {
            orderList.add(data.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMyClickListener(int position) {
        Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
        intent.putExtra(Content.ORDER_KEY, orderList.get(position).getCode());
        intent.putExtra(Content.STATUS,orderList.get(position).getIsAccept());

        startActivity(intent);
    }
}
