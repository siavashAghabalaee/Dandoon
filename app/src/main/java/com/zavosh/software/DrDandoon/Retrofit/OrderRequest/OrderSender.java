package com.zavosh.software.DrDandoon.Retrofit.OrderRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderSender {
    @SerializedName("IsKid")
    @Expose
    private String isKid;
    @SerializedName("CityId")
    @Expose
    private String cityId;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Images")
    @Expose
    private List<String> images = null;
    @SerializedName("OpgImage")
    @Expose
    private String opgImage;
    @SerializedName("Questions")
    @Expose
    private List<Question> questions = null;

    public String getIsKid() {
        return isKid;
    }

    public void setIsKid(String isKid) {
        this.isKid = isKid;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getOpgImage() {
        return opgImage;
    }

    public void setOpgImage(String opgImage) {
        this.opgImage = opgImage;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
