package com.zavosh.software.DrDandoon.Retrofit.RegisterRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResult {
    @SerializedName("userCode")
    @Expose
    private Integer userCode;
    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
