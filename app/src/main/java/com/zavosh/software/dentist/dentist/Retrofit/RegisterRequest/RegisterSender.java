package com.zavosh.software.dentist.dentist.Retrofit.RegisterRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterSender {
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("CellNumber")
    @Expose
    private String cellNumber;
    @SerializedName("roleName")
    @Expose
    private String roleName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
