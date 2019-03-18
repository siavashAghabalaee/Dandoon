package com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientOrder {

    @SerializedName("creationDate")
    @Expose
    private String creationDate;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("isAccept")
    @Expose
    private String isAccept;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
