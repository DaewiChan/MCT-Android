package com.example.mct.data.pref

import android.content.Context
import android.content.SharedPreferences

class AppPrefHelper(context: Context) {

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    /**
     * User preference
     * @return
     */

    fun putStringValue(key: String, value: String?) {
        mPrefs.edit().putString(key, value).commit()
    }

    fun getStringValue(key: String) = mPrefs.getString(key, null)

    fun putInt(key: String, value: Int) {
        mPrefs.edit().putInt(key, value).commit()
    }

    fun getInt(key: String) = mPrefs.getInt(key, -1)

    fun putBool(key: String, value: Boolean) {
        mPrefs.edit().putBoolean(key, value).commit()
    }

    fun getBool(key: String) = mPrefs.getBoolean(key, false)


    fun clearPref() {

        putBool(IS_LOGIN, false)
        putStringValue(USER_LOGIN_TOKEN, null.toString())
        putStringValue(ADMIN_LOGIN_TOKEN, null.toString())
        putStringValue(USER_TYPE, null.toString())
    }

    companion object {
        private const val PREF_FILE_NAME = "pref_soggy"

        const val IS_LOGIN = "is_login"
        const val USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN"
        const val ADMIN_LOGIN_TOKEN = "ADMIN_LOGIN_TOKEN"
        const val USER_TYPE = "USER_TYPE"

        private var sAppPrefHelper: AppPrefHelper? = null

        @Synchronized
        fun getInstance(context: Context): AppPrefHelper {
            if (sAppPrefHelper == null) {
                sAppPrefHelper = AppPrefHelper(context)
            }
            return sAppPrefHelper!!
        }
    }
}