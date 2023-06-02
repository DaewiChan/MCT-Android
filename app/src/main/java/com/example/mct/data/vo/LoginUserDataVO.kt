package com.example.mct.data.vo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class LoginUserDataVO {
    @Json(name = "id")
    val id:String?=null
    @Json(name = "nickName")
    var nickName: String? = null
    @Json(name = "avatar")
    var imgUrl: String? = null
    @Json(name = "gender")
    var gender: Int? = null
    @Json(name = "status")
    var status: Int? = null
    @Json(name = "type")
    var type: String? = null
    @Json(name = "type_text")
    var type_text: String? = null
    @Json(name = "last_name")
    var lastName: String? = null
    @Json(name = "first_name")
    var firstName: String? = null
    @Json(name = "phone_code")
    var phoneCode: String? = null
    @Json(name = "mask_email")
    var email: String? = null
    @Json(name = "mask_phone")
    var phoneNo: String? = null
}