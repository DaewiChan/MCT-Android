package com.example.mct.presentation.account.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.mct.R
import com.example.mct.base.BaseActivity
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.data.vo.GetVerifyCodeDataVO
import com.example.mct.presentation.account.register.RegisterActivity
import com.example.mct.presentation.main.MainActivity
import com.example.mct.utilities.CommonUtil
import com.example.mct.utilities.NetworkUtilities

enum class UserType{
    userLogin,
    adminLogin
}

class LoginActivity : BaseActivity<LoginPresenter>(),LoginView{
    var etEmail: EditText? = null
    var etCode: EditText? = null
    var llCode: LinearLayout? = null
    var lblCode: TextView? = null
    var etPassword: EditText? = null
    var btnLogin: Button? = null
    var lblRegister: TextView? = null
    var btnGetVerifyCode: TextView? = null
    var rbLoginUser: RadioButton? = null
    var rbLoginAdmin: RadioButton? = null
    var img_eye_password: ImageView? = null
    var img_eye_password_hide: ImageView? = null

    var userType: UserType = UserType.userLogin

    var pref: AppPrefHelper? = null

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = AppPrefHelper.getInstance(this)

        bindID()
        catchEvent()
    }

    fun bindID(){
        etEmail = findViewById(R.id.etEmail)
        etCode = findViewById(R.id.etVerifyCode)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        lblRegister = findViewById(R.id.lblHaveAcc)
        btnGetVerifyCode = findViewById(R.id.btnGetCode)
        rbLoginUser = findViewById(R.id.rbUser)
        rbLoginAdmin = findViewById(R.id.rbAdmin)
        lblCode = findViewById(R.id.lblVerifyCode)
        llCode = findViewById(R.id.llCode)
        img_eye_password = findViewById(R.id.img_eye_password)
        img_eye_password_hide = findViewById(R.id.img_eye_password_hide)
    }

    fun catchEvent(){
        btnLogin?.setOnClickListener {
        if (NetworkUtilities().isConnected(this)){
            if (userType == UserType.userLogin){
                if (hasValidData()){
                    var hashPassword = CommonUtil().sha256Hash(etPassword?.text.toString())
                    presenter.userLogin(etEmail?.text.toString(),hashPassword,etCode?.text.toString(),etCode?.text.toString(),"user")
                }
            }else{
                if (hasValidData()){
                    pref?.putStringValue(AppPrefHelper.ADMIN_LOGIN_TOKEN,"b1f3f9224b37e0798a1019d71c31c8fdd371f790")
                    pref?.putBool(AppPrefHelper.IS_LOGIN,true)
                    pref?.putStringValue(AppPrefHelper.USER_TYPE,"admin")
                    startActivity(MainActivity.newIntent(this))
                }

            }
        }else{
            NetworkUtilities().showPoorConnectionToast(this)
        }

       }

        lblRegister?.setOnClickListener {
            if (userType == UserType.userLogin){
                startActivity(RegisterActivity.newIntent(this))
            }

        }

        btnGetVerifyCode?.setOnClickListener {
            if (etEmail?.text.toString().isNotEmpty()){
                presenter.getVerifyCode(etEmail?.text.toString())
            }else{
                etEmail?.error = "Enter Email"
                etEmail?.requestFocus()
            }
        }

        rbLoginUser?.setOnClickListener {
            userType = UserType.userLogin
            userLoginUI()
        }

        rbLoginAdmin?.setOnClickListener {
            userType = UserType.adminLogin
            adminLoginUI()
        }

        img_eye_password?.setOnClickListener {
            // etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            etPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
            img_eye_password_hide?.visibility = View.VISIBLE
            img_eye_password?.visibility = View.GONE
        }

        img_eye_password_hide?.setOnClickListener {
            //  etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            etPassword?.transformationMethod = HideReturnsTransformationMethod.getInstance()
            img_eye_password_hide?.visibility = View.GONE
            img_eye_password?.visibility = View.VISIBLE
        }
    }

    fun hasValidData() : Boolean{

        if (etEmail?.text.toString().isEmpty()) {
            etEmail?.error = "Enter Email"
            etEmail?.requestFocus()
            return  false
        }else if (etPassword?.text.toString().isEmpty()){
            etPassword?.error = "Enter Password"
            etPassword?.requestFocus()
            return false
        }

        if (userType == UserType.userLogin){
            if (etCode?.text.toString().isEmpty()){
                Toast.makeText(this,"You need to get Verify Code first!!",Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return  true
    }

    fun adminLoginUI(){
        lblCode?.visibility = View.GONE
        llCode?.visibility = View.GONE
    }

    fun userLoginUI(){
        lblCode?.visibility = View.VISIBLE
        llCode?.visibility = View.VISIBLE
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this, AppPrefHelper.getInstance(this))
    }

    override fun showHome(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        startActivity(MainActivity.newIntent(this))
    }

    override fun showVerifyCode(data: GetVerifyCodeDataVO) {
        runOnUiThread {
            etCode?.setText(data.verifyCode)
        }
    }

    override fun showLoading() {
        runOnUiThread {
            mProgressDialog?.show()
        }

    }

    override fun hideLoading() {
        runOnUiThread {
            mProgressDialog?.hide()
        }
    }

    override fun showSuccess(message: String) {

    }

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}