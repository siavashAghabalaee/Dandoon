package com.zavosh.software.dentist.dentist.Activities.MVP_SelectImages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

public interface Contract_SelectImages {
    interface View{
        void setImage(ThreeImages images);
        void showDialogCameraAndGallery(int id);
        void showDialogDelete(int id);
        void showDialogCameraAndGalleryOpg();
        void setOpg(Bitmap opg);
        void showSampleDialog (int id);
        void showMessage(String message);
        void showProgressBar();
        void hideProgressBarr();
        void setMessageInMonitor(String message);
        void goHome();

    }
    interface Presenter{
        void attachView(View view , Context context);
        void imageClicked(int id);
        void opgClicked();
        void addImage(Bitmap bitmap);
        void addOpg(Bitmap bitmap);
        void deleteClicked(int id);
        void sampleClicked(int id);
        void sendClicked(String description);

        //------------------

        void setImage(ThreeImages images);
        void showDialogCameraAndGallery(int id);
        void showDialogCameraAndGalleryOpg();
        void showDialogDelete(int id);
        void setOpg(Bitmap opg);
        void showMessage(String message);
        void setMessageInMonitor(String message);
        void hideProgressBarr();
        void showProgressBar();
        void goHome();

    }
    interface Model{
        void attachPresenter(Presenter presenter , Context context);
        void imageClicked(int id);
        void addImage(Bitmap bitmap);
        void opgClicked();
        void addOpg(Bitmap bitmap);
        void deleteImage(int id);
        void send(String description);
    }
}
