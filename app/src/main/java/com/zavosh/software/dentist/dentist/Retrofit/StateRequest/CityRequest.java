package com.zavosh.software.dentist.dentist.Retrofit.StateRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

import java.util.List;

public class CityRequest {
    @SerializedName("result")
    @Expose
    private List<MyCity> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<MyCity> getResult() {
        return result;
    }

    public void setResult(List<MyCity> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
