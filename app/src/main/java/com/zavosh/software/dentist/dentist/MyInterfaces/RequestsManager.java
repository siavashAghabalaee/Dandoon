package com.zavosh.software.dentist.dentist.MyInterfaces;

public interface RequestsManager {
    void resendRequest(int id);
    void setMessageForProgressBar(String message , int id);
}
