package com.example.mct.data.remote.api

import com.example.mct.data.remote.request.*
import com.example.mct.data.remote.response.GetVerifyCodeResponse
import com.example.mct.data.remote.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface UserApi {

    @Headers("Content-Type: application/json",
             "Accept: application/json")
    @POST("user/passport/login_by_email")
    fun userLoginByEmail(
        @Body loginByEmailRequest: LoginByEmailRequest?
    ): Observable<LoginResponse>

    @Headers("Content-Type: application/json",
             "Accept: application/json")
    @POST("admin/passport/login")
    fun adminLogin(
        @Body adminLoginRequest: AdminLoginRequest?
    ): Observable<LoginResponse>

    @Headers("Content-Type: application/json",
        "Accept: application/json")
    @POST("user/passport/reg_by_email")
    fun userRegisterByEmail(
        @Body registerByEmailRequest: RegisterByEmailRequest?
    ): Observable<LoginResponse>

    @Headers("Content-Type: application/json",
        "Accept: application/json")
    @POST("user/user/info")
    fun getUserInfo(
        @Header("Mct-Token") token: String? = null,
        @Header("Accept-Language") language: String? = null,
    ): Observable<LoginResponse>

    @Headers("Content-Type: application/json",
        "Accept: application/json")
    @POST("user/common/send_code")
    fun getVerifyCode(
        @Body getVerifyCodeRequest: GetVerifyCodeRequest?
    ): Observable<GetVerifyCodeResponse>

}