package com.zavosh.software.dentist.dentist.Retrofit.ForgotPasRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassSender {
    @SerializedName("cellNumber")
    @Expose
    private String cellNumber;
    @SerializedName("roleName")
    @Expose
    private String roleName;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("deviceModel")
    @Expose
    private String deviceModel;
    @SerializedName("OsType")
    @Expose
    private String osType;
    @SerializedName("OsVersion")
    @Expose
    private String osVersion;

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
