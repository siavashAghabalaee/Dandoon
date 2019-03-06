package com.zavosh.software.DrDandoon.Retrofit.RegisterRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.DrDandoon.Retrofit.Status;

public class RegisterRequest {
    @SerializedName("result")
    @Expose
    private RegisterResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public RegisterResult getResult() {
        return result;
    }

    public void setResult(RegisterResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
