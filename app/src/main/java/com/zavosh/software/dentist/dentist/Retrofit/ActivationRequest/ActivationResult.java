package com.zavosh.software.dentist.dentist.Retrofit.ActivationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivationResult {
    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
