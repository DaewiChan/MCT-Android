package com.example.mct.data.remote

import com.example.mct.data.remote.api.UserApi

object ApiFactory {
    val userApi: UserApi = RetrofitFactory.retrofit(AppConstants.baseApi).create(UserApi::class.java)
}