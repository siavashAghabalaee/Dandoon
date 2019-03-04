package com.zavosh.software.dentist.dentist.Retrofit;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://dentistapi.zavoshsoftware.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
