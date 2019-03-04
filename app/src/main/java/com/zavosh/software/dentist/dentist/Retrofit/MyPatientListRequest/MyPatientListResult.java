package com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPatientListResult {
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
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerCellNumber")
    @Expose
    private String customerCellNumber;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCellNumber() {
        return customerCellNumber;
    }

    public void setCustomerCellNumber(String customerCellNumber) {
        this.customerCellNumber = customerCellNumber;
    }
}
