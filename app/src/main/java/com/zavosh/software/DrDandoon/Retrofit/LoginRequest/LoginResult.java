package com.zavosh.software.DrDandoon.Retrofit.LoginRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.DrDandoon.Retrofit.Status;

public class LoginResult {
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private Status status;

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
