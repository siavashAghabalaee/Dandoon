package com.zavosh.software.dentist.dentist.Retrofit;

import com.zavosh.software.dentist.dentist.Retrofit.ActivationRequest.ActivationRequest;
import com.zavosh.software.dentist.dentist.Retrofit.ActivationRequest.ActivationSender;
import com.zavosh.software.dentist.dentist.Retrofit.CheckVersionRequest.CheckVersionRequest;
import com.zavosh.software.dentist.dentist.Retrofit.FilterItemRequest.FilterItemRequest;
import com.zavosh.software.dentist.dentist.Retrofit.ForgotPasRequest.ForgotPassSender;
import com.zavosh.software.dentist.dentist.Retrofit.ForgotPasRequest.ForgotPassResult;
import com.zavosh.software.dentist.dentist.Retrofit.HomeSickRequest.HomeSickRequest;
import com.zavosh.software.dentist.dentist.Retrofit.LoginRequest.LoginResult;
import com.zavosh.software.dentist.dentist.Retrofit.LoginRequest.LoginSender;
import com.zavosh.software.dentist.dentist.Retrofit.MyPatientListRequest.MyPatientListRequest;
import com.zavosh.software.dentist.dentist.Retrofit.OrderDetailRequest.OrderDetailRequest;
import com.zavosh.software.dentist.dentist.Retrofit.OrderRequest.OrderRequest;
import com.zavosh.software.dentist.dentist.Retrofit.OrderRequest.OrderSender;
import com.zavosh.software.dentist.dentist.Retrofit.PatientListRequest.PatientListRequest;
import com.zavosh.software.dentist.dentist.Retrofit.PostPurchase.PostPurchaseRequest;
import com.zavosh.software.dentist.dentist.Retrofit.RegisterRequest.RegisterRequest;
import com.zavosh.software.dentist.dentist.Retrofit.RegisterRequest.RegisterSender;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.CityRequest;
import com.zavosh.software.dentist.dentist.Retrofit.StateRequest.StateRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @Headers("Content-Type: application/json")
    @POST("account/login")
    Call<LoginResult> login(@Body LoginSender loginSender);

    @Headers("Content-Type: application/json")
    @POST("account/forget")
    Call<ForgotPassResult> forgetPassRequest(@Body ForgotPassSender forgotPassSender);

    @Headers("Content-Type: application/json")
    @POST("account/register")
    Call<RegisterRequest> registerRequest(@Body RegisterSender registerSender);

    @Headers("Content-Type: application/json")
    @POST("account/activate")
    Call<ActivationRequest> activationRequest(@Body ActivationSender activationSender);

    @Headers("Content-Type: application/json")
    @GET("LatestVersion")
    Call<CheckVersionRequest> checkVersionRequest(@Query("osType") String osType);

    @Headers("Content-Type: application/json")
    @GET("home/get")
    Call<HomeSickRequest> homeSickRequest(@Header("Authorization") String token, @Query("version") String version , @Query("osType") String osType);

    @Headers("Content-Type: application/json")
    @GET("Provinces/get")
    Call<StateRequest> getState();

    @Headers("Content-Type: application/json")
    @GET("city/get")
    Call<CityRequest> getCity(@Query("provinceId") String stateId);

    @Headers("Content-Type: application/json")
    @POST("order/post")
    Call<OrderRequest> postOrder(@Header("Authorization") String token,@Body OrderSender orderSender);

    @Headers("Content-Type: application/json")
    @GET("order/list/get")
    Call<PatientListRequest> getPatientList(@Header("Authorization") String token , @Query("typeid") String typeid);

    @Headers("Content-Type: application/json")
    @GET("type/list/get")
    Call<FilterItemRequest> getFilterList (@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("order/detail/get")
    Call<OrderDetailRequest> getOrderDetail(@Header("Authorization") String token , @Query("orderCode") String orderCode);

    @POST("order/postPurchase")
    @FormUrlEncoded
    Call<PostPurchaseRequest> postPurchase(@Header("Authorization") String token, @Field("OrderCode") String OrderCode);

    @Headers("Content-Type: application/json")
    @GET("order/list/getPurchased")
    Call<MyPatientListRequest> getMyPatientList (@Header("Authorization") String token , @Query("typeid") String typeid);
}
