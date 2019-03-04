package com.zavosh.software.dentist.dentist.Retrofit.OrderRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("QuestionId")
    @Expose
    private Integer questionId;
    @SerializedName("ResponseId")
    @Expose
    private List<String> responseId = null;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<String> getResponseId() {
        return responseId;
    }

    public void setResponseId(List<String> responseId) {
        this.responseId = responseId;
    }
}
