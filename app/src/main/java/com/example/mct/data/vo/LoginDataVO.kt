package com.example.mct.data.vo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class LoginDataVO {
    @Json(name = "token")
    val token:String?=null
    @Json(name = "user")
    var loginUserData: LoginUserDataVO? = null
}