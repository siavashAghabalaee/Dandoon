package com.zavosh.software.DrDandoon.Retrofit.StateRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyState {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return title;
    }

    public void setState(String state) {
        this.title = state;
    }
}
