package com.zavosh.software.dentist.dentist.Retrofit.ActivationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class ActivationRequest {
    @SerializedName("result")
    @Expose
    private ActivationResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public ActivationResult getResult() {
        return result;
    }

    public void setResult(ActivationResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
