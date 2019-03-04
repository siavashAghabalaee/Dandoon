package com.zavosh.software.dentist.dentist.Retrofit.StateRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

import java.util.List;

public class StateRequest {
    @SerializedName("result")
    @Expose
    private List<MyState> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<MyState> getResult() {
        return result;
    }

    public void setResult(List<MyState> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
