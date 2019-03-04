package com.zavosh.software.dentist.dentist.Retrofit.PostPurchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class PostPurchaseRequest {
    @SerializedName("result")
    @Expose
    private PostPurchaseResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public PostPurchaseResult getResult() {
        return result;
    }

    public void setResult(PostPurchaseResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
