package com.zavosh.software.dentist.dentist.Activities.MVP_SelectImages;

import android.graphics.Bitmap;

public class ThreeImages {
    public Bitmap imageOne = null;
    public Bitmap imageTwo = null;
    public Bitmap imageThree = null;
    public Bitmap opg = null;

    public void addImage (Bitmap bitmap){
        if (imageOne == null){
            imageOne = bitmap;
        }else if (imageTwo == null){
            imageTwo = bitmap;
        }else if (imageThree == null){
            imageThree = bitmap;
        }
    }

    public void deleteImage (int number){
        if (number == 1){
            imageOne = imageTwo;
            imageTwo = imageThree;
            imageThree = null;
        }else if (number == 2){
            imageTwo = imageThree;
            imageThree = null;
        }else if (number == 3){
            imageThree = null;
        }
    }

    public int getCount(){
        if (imageThree == null && imageTwo == null && imageOne == null){
            return 0;
        }else if (imageOne != null && imageTwo == null && imageThree == null){
            return 1;
        }else if (imageOne != null && imageTwo != null && imageThree == null){
            return 2;
        }else if (imageOne != null && imageTwo != null && imageThree != null){
            return 3;
        }else {
            return -1;
        }
    }

    public Bitmap getImageOne() {
        return imageOne;
    }

    public Bitmap getImageTwo() {
        return imageTwo;
    }

    public Bitmap getImageThree() {
        return imageThree;
    }

    public Bitmap getOpg() {
        return opg;
    }

    public void setOpg(Bitmap opg) {
        this.opg = opg;
    }
}
