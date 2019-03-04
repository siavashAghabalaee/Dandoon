package com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

import java.util.List;

public class PatientListRequest {
    @SerializedName("result")
    @Expose
    private List<PatientItemResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<PatientItemResult> getResult() {
        return result;
    }

    public void setResult(List<PatientItemResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
