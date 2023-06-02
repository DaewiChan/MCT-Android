package com.example.mct.data.remote.response

import com.example.mct.data.vo.GetVerifyCodeDataVO
import com.example.mct.data.vo.LoginDataVO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class GetVerifyCodeResponse {
    @Json(name = "code")
    val code:Int?=null
    @Json(name = "data")
    var verifyCodeData: GetVerifyCodeDataVO? = null
    @Json(name = "message")
    var message: String?= null
}



//{
//    "code": 0,
//    "data": {
//    "validity_period": 300,
//    "countdown": 60,
//    "method": "email",
//    "dev:verify_code": "384776"
//},
//    "message": "Tip.发送成功"
//}