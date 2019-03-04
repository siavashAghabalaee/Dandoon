package com.zavosh.software.dentist.dentist.Retrofit.HomeSickRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class HomeSickRequest {
    @SerializedName("result")
    @Expose
    private HomeSickResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public HomeSickResult getResult() {
        return result;
    }

    public void setResult(HomeSickResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
