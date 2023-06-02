package com.example.mct.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mct.R
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.presentation.account.login.LoginActivity
import com.example.mct.presentation.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initUI()
    }

    fun initUI() {
        Handler().postDelayed({
            val prefHelper = AppPrefHelper.getInstance(applicationContext)
            val isLogin = prefHelper.getBool(AppPrefHelper.IS_LOGIN)
            if (isLogin){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 1500)
    }
}