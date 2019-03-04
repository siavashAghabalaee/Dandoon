package com.zavosh.software.dentist.dentist.Retrofit.ForgotPasRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class ForgotPassResult {
    @SerializedName("result")
    @Expose
    private String result;
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
