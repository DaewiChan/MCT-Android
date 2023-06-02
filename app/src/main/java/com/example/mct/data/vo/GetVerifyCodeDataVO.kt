package com.example.mct.data.vo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class GetVerifyCodeDataVO {
    @Json(name = "validity_period")
    val validityPeriod:Int?=null
    @Json(name = "countdown")
    var countdown: Int? = null
    @Json(name = "method")
    var method: String? = null
    @Json(name = "dev:verify_code")
    var verifyCode: String? = null
}

//"data": {
//    "validity_period": 300,
//    "countdown": 60,
//    "method": "email",
//    "dev:verify_code": "384776"
//}