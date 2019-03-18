package com.zavosh.software.DrDandoon.Retrofit.HomeSickRequest;

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

    @SerializedName("aboutText")
    @Expose
    private String aboutText;

    @SerializedName("inviteText")
    @Expose
    private String inviteText;

    @SerializedName("supportPhone")
    @Expose
    private String supportPhone;

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

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public String getInviteText() {
        return inviteText;
    }

    public void setInviteText(String inviteText) {
        this.inviteText = inviteText;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }
}
