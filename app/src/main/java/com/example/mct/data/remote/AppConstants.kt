package com.example.mct.data.remote

import com.example.mct.NativeStore

object AppConstants {
    var baseApi = NativeStore().stringFromJNI()
}