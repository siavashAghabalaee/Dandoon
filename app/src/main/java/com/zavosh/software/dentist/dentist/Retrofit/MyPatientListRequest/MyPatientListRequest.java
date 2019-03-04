package com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

import java.util.List;

public class MyPatientListRequest {
    @SerializedName("result")
    @Expose
    private List<MyPatientListResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<MyPatientListResult> getResult() {
        return result;
    }

    public void setResult(List<MyPatientListResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
