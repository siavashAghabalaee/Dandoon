package com.zavosh.software.DrDandoon.Retrofit.CancelOrderRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.DrDandoon.Retrofit.Status;

public class CancelOrderRequest {
    @SerializedName("result")
    @Expose
    private String  result;

    @SerializedName("status")
    @Expose
    private Status status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
