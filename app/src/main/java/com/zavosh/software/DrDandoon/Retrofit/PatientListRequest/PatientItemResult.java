package com.zavosh.software.DrDandoon.Retrofit.PatientListRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientItemResult {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("kidStatus")
    @Expose
    private String kidStatus;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("typeTitle")
    @Expose
    private String typeTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
