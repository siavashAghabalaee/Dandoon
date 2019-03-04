package com.zavosh.software.dentist.dentist.Retrofit.PostPurchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostPurchaseResult {
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("cellNumber")
    @Expose
    private String cellNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

}
