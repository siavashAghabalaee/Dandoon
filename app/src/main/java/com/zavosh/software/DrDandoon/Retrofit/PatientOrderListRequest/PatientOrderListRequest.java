package com.zavosh.software.DrDandoon.Retrofit.PatientOrderListRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.DrDandoon.Retrofit.Status;

import java.util.List;

public class PatientOrderListRequest {
    @SerializedName("result")
    @Expose
    private List<PatientOrder> result;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<PatientOrder> getResult() {
        return result;
    }

    public void setResult(List<PatientOrder> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
