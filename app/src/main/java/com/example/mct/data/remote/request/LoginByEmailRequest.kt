package com.example.mct.data.remote.request

data class LoginByEmailRequest (
    val email: String?,
    val password: String?,
    val verify_code: String?,
    val s_code: String?
        )