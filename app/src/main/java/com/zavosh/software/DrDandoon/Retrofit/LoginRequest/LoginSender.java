package com.zavosh.software.DrDandoon.Retrofit.LoginRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSender {
    @SerializedName("CellNumber")
    @Expose
    private String cellNumber;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("RoleName")
    @Expose
    private String roleName;

    public LoginSender(String cellNumber, String password, String roleName) {
        this.cellNumber = cellNumber;
        this.password = password;
        this.roleName = roleName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
