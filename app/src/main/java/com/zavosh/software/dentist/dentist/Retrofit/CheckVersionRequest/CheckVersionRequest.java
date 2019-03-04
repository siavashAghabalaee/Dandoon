package com.zavosh.software.dentist.dentist.Retrofit.CheckVersionRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class CheckVersionRequest {
    @SerializedName("result")
    @Expose
    private CheckVersionResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public CheckVersionResult getResult() {
        return result;
    }

    public void setResult(CheckVersionResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
