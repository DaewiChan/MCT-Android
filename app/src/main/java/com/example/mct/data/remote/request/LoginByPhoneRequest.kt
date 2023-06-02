package com.example.mct.data.remote.request

data class LoginByPhoneRequest (
    val phone_code: String?,
    val phone: String?,
    val password: String?,
    val verify_code: String?
        )