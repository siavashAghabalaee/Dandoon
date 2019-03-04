package com.zavosh.software.dentist.dentist.Retrofit.OrderRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class OrderRequest {
    @SerializedName("result")
    @Expose
    public Result result;
    @SerializedName("status")
    @Expose
    public Status status;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
