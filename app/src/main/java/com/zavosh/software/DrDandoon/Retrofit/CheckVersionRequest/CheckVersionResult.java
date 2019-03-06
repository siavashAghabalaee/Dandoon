package com.zavosh.software.DrDandoon.Retrofit.CheckVersionRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckVersionResult {
    @SerializedName("versionNumber")
    @Expose
    private String versionNumber;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("isNeccessary")
    @Expose
    private Boolean isNeccessary;
    @SerializedName("latestStableVersion")
    @Expose
    private String latestStableVersion;
    @SerializedName("isBeta")
    @Expose
    private Boolean isBeta;

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getIsNeccessary() {
        return isNeccessary;
    }

    public void setIsNeccessary(Boolean isNeccessary) {
        this.isNeccessary = isNeccessary;
    }

    public String getLatestStableVersion() {
        return latestStableVersion;
    }

    public void setLatestStableVersion(String latestStableVersion) {
        this.latestStableVersion = latestStableVersion;
    }

    public Boolean getIsBeta() {
        return isBeta;
    }

    public void setIsBeta(Boolean isBeta) {
        this.isBeta = isBeta;
    }

}
