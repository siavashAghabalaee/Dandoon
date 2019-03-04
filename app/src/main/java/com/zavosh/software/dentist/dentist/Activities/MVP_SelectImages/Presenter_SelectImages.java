package com.zavosh.software.dentist.dentist.Activities.MVP_SelectImages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

public class Presenter_SelectImages implements Contract_SelectImages.Presenter {
    private Contract_SelectImages.View view;
    private Contract_SelectImages.Model model;
    @Override
    public void attachView(Contract_SelectImages.View view, Context context) {
        this.view = view;
        model = new Model_SelectImages();
        model.attachPresenter(this,context);
    }

    @Override
    public void imageClicked(int id) {
        model.imageClicked(id);
    }

    @Override
    public void opgClicked() {
        model.opgClicked();
    }


    @Override
    public void addImage(Bitmap bitmap) {
        model.addImage(bitmap);
    }

    @Override
    public void addOpg(Bitmap bitmap) {
        model.addOpg(bitmap);
    }

    @Override
    public void deleteClicked(int id) {
        model.deleteImage(id);
    }

    @Override
    public void sampleClicked(int id) {
        view.showSampleDialog(id);
    }

    @Override
    public void sendClicked(String description) {
        model.send(description);

    }


    @Override
    public void setImage(ThreeImages images) {
        view.setImage(images);
    }

    @Override
    public void showDialogCameraAndGallery(int id) {
        view.showDialogCameraAndGallery(id);
    }

    @Override
    public void showDialogCameraAndGalleryOpg() {
        view.showDialogCameraAndGalleryOpg();
    }

    @Override
    public void showDialogDelete(int id) {
        view.showDialogDelete(id);
    }

    @Override
    public void setOpg(Bitmap opg) {
        view.setOpg(opg);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void setMessageInMonitor(String message) {
        view.setMessageInMonitor(message);
    }

    @Override
    public void hideProgressBarr() {
        view.hideProgressBarr();
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void goHome() {
        view.goHome();
    }

}
