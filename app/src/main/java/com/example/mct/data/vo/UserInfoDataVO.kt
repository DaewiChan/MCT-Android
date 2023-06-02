package com.example.mct.data.vo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class UserInfoDataVO {
    @Json(name = "id")
    val id:String?=null
    @Json(name = "nickName")
    var nickName: String? = null
    @Json(name = "avatar")
    var imgUrl: String? = null
    @Json(name = "create_time")
    var createTime: String? = null
    @Json(name = "credit_balance")
    var creditBalance: String? = null
    @Json(name = "type")
    var type: String? = null
    @Json(name = "type_text")
    var type_text: String? = null
    @Json(name = "last_name")
    var lastName: String? = null
    @Json(name = "first_name")
    var firstName: String? = null
     @Json(name = "company_name")
    var companyName: String? = null
    @Json(name = "service_fee")
    var serviceFee: String? = null
    @Json(name = "mask_email")
    var email: String? = null
    @Json(name = "mask_phone")
    var phoneNo: String? = null
}

//"data": {
//    "user": {
//        "id": "30",
//        "nickname": "hy405056",
//        "avatar": "https://mctremit.com/operation/public/user/attachments/avatar/1683439738_93debc45187ae618b636.png",
//        "create_time": "2023-05-07 14:06:22",
//        "credit_balance": "1000.00",
//        "type": "personal",
//        "type_text": "Fields.个人账号",
//        "login_time": "2023-05-07 00:00:00",
//        "login_ip": "27.17.166.16",
//        "last_name": "",
//        "first_name": "",
//        "company_name": "",
//        "service_fee": "15.00",
//        "mask_phone": "",
//        "mask_email": "***56@163.com",
//        "mask_epass": "**********",
//        "is_lock_type": 0,
//        "is_certified": 0,
//        "collection_amount_stat": {
//        "HKD": "0.00",
//        "SGD": "0.00",
//        "USD": "0.00",
//        "CNY": "0.00",
//        "JPY": "0.00"
//    },
//        "remit_amount_stat": "0.00"
//    },
//    "last_record": {
//        "id": "59",
//        "last_name": "严",
//        "first_name": "号",
//        "address": "少了科技啊",
//        "zip_code": "43211",
//        "country_id": "1",
//        "province_id": "2",
//        "city_id": "6",
//        "country_name": "China",
//        "province_name": "Guangdong",
//        "city_name": "Guangzhou",
//        "default_lang": "en",
//        "birthday": "2019-05-01",
//        "status": 4,
//        "idcard_no": "6113",
//        "status_text": "Fields.请求更多信息",
//        "review_time": "2023-05-07 14:25:11",
//        "reject_reason": "",
//        "submit_time": "2023-05-07 14:21:04",
//        "create_time": "2023-05-07 14:21:04",
//        "idcard_images": [
//        {
//            "id": "26",
//            "user_info_id": "59",
//            "path": "https://mctremit.com/operation/public/user/attachments/file/1683440097_c21f313da9eff1ba3752.png",
//            "status": "1",
//            "create_time": "2023-05-07 14:21:04",
//            "update_time": null
//        }
//        ],
//        "live_images": [
//        {
//            "id": "25",
//            "user_info_id": "59",
//            "path": "https://mctremit.com/operation/public/user/attachments/file/1683440116_2208822a5d0a7b20f4b6.png",
//            "status": "1",
//            "create_time": "2023-05-07 14:21:04",
//            "update_time": null
//        }
//        ],
//        "gender": {
//        "id": "1",
//        "code": "MALE",
//        "name": "Male"
//    }
//    }