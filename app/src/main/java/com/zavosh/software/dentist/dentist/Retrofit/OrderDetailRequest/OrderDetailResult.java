package com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.OrderRequest.Question;

import java.util.List;

public class OrderDetailResult {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("doctorInfo")
    @Expose
    private String doctorInfo;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("opgImage")
    @Expose
    private String opgImage;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("kidStatus")
    @Expose
    private String kidStatus;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("typeTitle")
    @Expose
    private String typeTitle;
    @SerializedName("questions")
    @Expose
    private List<NewQuestion> questions = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(String doctorInfo) {
        this.doctorInfo = doctorInfo;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKidStatus() {
        return kidStatus;
    }

    public void setKidStatus(String kidStatus) {
        this.kidStatus = kidStatus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public List<NewQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<NewQuestion> questions) {
        this.questions = questions;
    }
}
