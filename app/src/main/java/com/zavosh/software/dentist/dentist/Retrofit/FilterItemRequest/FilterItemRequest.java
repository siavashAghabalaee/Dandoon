package com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

import java.util.List;

public class FilterItemRequest {

    @SerializedName("result")
    @Expose
    private List<FilterItem> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<FilterItem> getResult() {
        return result;
    }

    public void setResult(List<FilterItem> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
