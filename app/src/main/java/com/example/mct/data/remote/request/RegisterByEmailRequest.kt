package com.example.mct.data.remote.request

data class RegisterByEmailRequest (
    val email: String?,
    val s_code: String?,
    val password: String?,
    val phone: String?,
    val phone_code: String?
        )