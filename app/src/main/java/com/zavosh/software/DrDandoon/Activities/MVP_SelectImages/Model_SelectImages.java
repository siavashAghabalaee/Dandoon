package com.zavosh.software.DrDandoon.Activities.MVP_SelectImages;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.zavosh.software.DrDandoon.Activities.MVP_ToothSelection.ToothSelectionActivity;
import com.zavosh.software.DrDandoon.Helper.CheckResponse;
import com.zavosh.software.DrDandoon.Helper.FabricSender;
import com.zavosh.software.DrDandoon.Helper.PublicMethods;
import com.zavosh.software.DrDandoon.MyInterfaces.RequestsManager;
import com.zavosh.software.DrDandoon.R;
import com.zavosh.software.DrDandoon.Retrofit.APIService;
import com.zavosh.software.DrDandoon.Retrofit.ApiUtils;
import com.zavosh.software.DrDandoon.Retrofit.OrderRequest.OrderRequest;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model_SelectImages implements Contract_SelectImages.Model , RequestsManager {
    private Contract_SelectImages.Presenter presenter;
    private Context context;
    private ThreeImages manager;
    private String description;

    @Override
    public void attachPresenter(Contract_SelectImages.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        manager = new ThreeImages();
        presenter.setImage(manager);
    }

    @Override
    public void imageClicked(int id) {
        int count = manager.getCount();
        if (id == ++count){
            //add image
            presenter.showDialogCameraAndGallery(id);
        }else if (id <= manager.getCount()){
            //delete
            presenter.showDialogDelete(id);
        }
    }

    @Override
    public void addImage(Bitmap bitmap) {
        manager.addImage(bitmap);
        presenter.setImage(manager);
    }

    @Override
    public void opgClicked() {
        presenter.showDialogCameraAndGalleryOpg();
    }

    @Override
    public void addOpg(Bitmap bitmap) {
        manager.setOpg(bitmap);
        presenter.setOpg(bitmap);
    }

    @Override
    public void deleteImage(int id) {
        manager.deleteImage(id);
        presenter.setImage(manager);
    }

    @Override
    public void send(final String description) {
        if (manager.getCount()>0) {
            presenter.showProgressBar();
            this.description = description;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ToothSelectionActivity.orderSender.setDescription(description);
                    List<String> imageList = new ArrayList<>();
                    if (manager.getImageOne() != null) {
                        imageList.add(Base64.encodeToString(getByte(manager.getImageOne()), Base64.DEFAULT));
                    }
                    if (manager.getImageTwo() != null) {
                        imageList.add(Base64.encodeToString(getByte(manager.getImageTwo()), Base64.DEFAULT));
                    }
                    if (manager.getImageThree() != null) {
                        imageList.add(Base64.encodeToString(getByte(manager.getImageThree()), Base64.DEFAULT));
                    }

                    ToothSelectionActivity.orderSender.setImages(imageList);
                    if (manager.getOpg() != null) {
                        ToothSelectionActivity.orderSender.setOpgImage(Base64.encodeToString(getByte(manager.getOpg()), Base64.DEFAULT));
                    }
                    sendRequest();
                }
            }).start();
        }else {
            presenter.showMessage(context.getString(R.string.pleaseSetImage));
        }
    }

    public byte[] getByte(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return byteArray;
    }

    @Override
    public void resendRequest(int id) {
        send(description);
    }

    @Override
    public void setMessageForProgressBar(String message,int id) {
        presenter.hideProgressBarr();
        presenter.showMessage(message);
    }

    public void sendRequest(){
        APIService apiService = ApiUtils.getAPIService();
        Call<OrderRequest> orderRequestCall = apiService.postOrder(PublicMethods.loadData(PublicMethods.TOKEN_ID,""),ToothSelectionActivity.orderSender);
        orderRequestCall.enqueue(new Callback<OrderRequest>() {
            @Override
            public void onResponse(Call<OrderRequest> call, Response<OrderRequest> response) {
                //Log.i("kk",response.code()+" :code");
                CheckResponse checkResponse = new CheckResponse();
                checkResponse.requestsManager = Model_SelectImages.this;
                if (checkResponse.checkRequestCode(response.code(),context,1) && checkResponse.checkStatus(response.code(),response.body().getStatus(),context,1)){
                    presenter.goHome();
                    presenter.showMessage(response.body().getStatus().getMessage());

                    FabricSender.addOrder();
                }
            }

            @Override
            public void onFailure(Call<OrderRequest> call, Throwable t) {
                presenter.showMessage(context.getString(R.string.androidErrorRequest));
                presenter.hideProgressBarr();
            }
        });
    };
}
