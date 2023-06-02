package com.example.mct.data.remote.response

import com.example.mct.data.vo.LoginDataVO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class LoginResponse {
    @Json(name = "code")
    val code:Int?=null
    @Json(name = "data")
    var loginData: LoginDataVO? = null
    @Json(name = "message")
    var message: String?= null
}

//D/OkHttp: {
//    D/OkHttp:     "code": 0,
//    D/OkHttp:     "data": {
//        D/OkHttp:         "token": "ff180cce52babf886045a35e8b801199bee95b78",
//        D/OkHttp:         "user": {
//        D/OkHttp:             "id": "35",
//        D/OkHttp:             "uid": "MCT64785428270b6",
//        D/OkHttp:             "nickname": "MCT_SnxhPEkh2622",
//        D/OkHttp:             "avatar": "",
//        D/OkHttp:             "gender": 0,
//        D/OkHttp:             "status": 1,
//        D/OkHttp:             "type": "",
//        D/OkHttp:             "type_text": "未设置",
//        D/OkHttp:             "last_name": "",
//        D/OkHttp:             "first_name": "",
//        D/OkHttp:             "phone_code": "+65",
//        D/OkHttp:             "mask_email": "***st@gmail.com",
//        D/OkHttp:             "mask_phone": "097*****00"
//        D/OkHttp:         }
//        D/OkHttp:     },
//    D/OkHttp:     "message": "Tip.注册成功"
//    D/OkHttp: }