package com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.software.dentist.dentist.Retrofit.Status;

public class OrderDetailRequest {
    @SerializedName("result")
    @Expose
    private OrderDetailResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public OrderDetailResult getResult() {
        return result;
    }

    public void setResult(OrderDetailResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
