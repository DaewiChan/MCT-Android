package com.example.mct.data.remote.request

data class GetVerifyCodeRequest (
    val email: String?,
    val scene: String?,
    val method: String?
        )