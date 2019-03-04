package com.zavosh.software.dentist.dentist.Retrofit.StateRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCity {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
