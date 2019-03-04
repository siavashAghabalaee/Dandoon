package com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewQuestion {
    @SerializedName("questionId")
    @Expose
    private String questionId;
    @SerializedName("responseId")
    @Expose
    private List<String> responseId = null;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getResponseId() {
        return responseId;
    }

    public void setResponseId(List<String> responseId) {
        this.responseId = responseId;
    }
}
