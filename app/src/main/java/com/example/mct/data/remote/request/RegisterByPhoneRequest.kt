package com.example.mct.data.remote.request

data class RegisterByPhoneRequest (
    val phone_code: String?,
    val phone: String?,
    val s_code: String?,
    val password: String?
        )