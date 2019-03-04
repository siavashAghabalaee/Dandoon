package com.zavosh.software.dentist.dentist.Retrofit.ActivationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivationSender {
    @SerializedName("tokenId")
    @Expose
    private String tokenId;
    @SerializedName("activationCode")
    @Expose
    private String activationCode;
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

    public ActivationSender(String tokenId, String activationCode, String deviceId, String deviceModel, String osType, String osVersion) {
        this.tokenId = tokenId;
        this.activationCode = activationCode;
        this.deviceId = deviceId;
        this.deviceModel = deviceModel;
        this.osType = osType;
        this.osVersion = osVersion;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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

    @Override
    public String toString() {
        return "ActivationSender{" +
                "tokenId='" + tokenId + '\'' +
                ", activationCode='" + activationCode + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", osType='" + osType + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
