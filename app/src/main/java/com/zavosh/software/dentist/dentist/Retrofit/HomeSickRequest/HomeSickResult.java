package com.zavosh.software.dentist.dentist.Retrofit.HomeSickRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeSickResult {
    @SerializedName("headerImages")
    @Expose
    private List<String> headerImages = null;
    @SerializedName("cellNumber")
    @Expose
    private String cellNumber;
    @SerializedName("fullName")
    @Expose
    private String fullName;

    public List<String> getHeaderImages() {
        return headerImages;
    }

    public void setHeaderImages(List<String> headerImages) {
        this.headerImages = headerImages;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
