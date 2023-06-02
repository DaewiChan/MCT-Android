package com.example.mct.presentation.account.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import com.example.mct.R
import com.example.mct.base.BaseActivity
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.data.vo.GetVerifyCodeDataVO
import com.example.mct.presentation.account.login.LoginActivity
import com.example.mct.utilities.CommonUtil
import com.example.mct.utilities.NetworkUtilities

class RegisterActivity : BaseActivity<RegisterPresenter>(),RegisterView {

    var etEmail: EditText? = null
    var etCode: EditText? = null
    var etPassword: EditText? = null
    var etConfirmPassword: EditText? = null
    var btnRegister: Button? = null
    var btnGetCode: Button? = null
    var lblLogin: TextView? = null
    var spPhoneCode: Spinner? = null
    var etPhoneNo: EditText? = null
    var img_eye_password: ImageView? = null
    var img_eye_password_hide: ImageView? = null
    var img_eye_confirm_password: ImageView? = null
    var img_eye_confirm_password_hide: ImageView? = null

    var phoneCodeStrLists = arrayOf("+65","+95")
    var selectPhoneCode: String? = null

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, RegisterActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initUI()

    }

    fun initUI(){
        etEmail = findViewById(R.id.etEmail)
        etCode = findViewById(R.id.etCode)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        lblLogin = findViewById(R.id.lblLogin)
        btnGetCode = findViewById(R.id.btnGetCode)
        spPhoneCode = findViewById(R.id.spPhoneCode)
        etPhoneNo = findViewById(R.id.etPhoneNo)
        img_eye_password = findViewById(R.id.img_eye_password)
        img_eye_password_hide = findViewById(R.id.img_eye_password_hide)
        img_eye_confirm_password = findViewById(R.id.img_eye_confrim_password)
        img_eye_confirm_password_hide = findViewById(R.id.img_eye_confrim_password_hide)

        catchEvent()
        mockPhoneCode()
    }

    fun catchEvent(){
        btnRegister?.setOnClickListener {
            if (NetworkUtilities().isConnected(this)){
                if (hasValidData()){
                    var hashPassword = CommonUtil().sha256Hash(etPassword?.text.toString())
                    presenter.userRegister(etEmail?.text.toString(),hashPassword,etCode?.text.toString(),selectPhoneCode?:"",etPhoneNo?.text.toString())
                }
            }else{
                NetworkUtilities().showPoorConnectionToast(this)
            }
        }

        btnGetCode?.setOnClickListener {
            if (etEmail?.text.toString().isNotEmpty()){
                presenter.getVerifyCode(etEmail?.text.toString())
            }else{
                etEmail?.error = "Enter Email"
                etEmail?.requestFocus()
            }
        }

        lblLogin?.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
        }


        spPhoneCode?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selectPhoneCode = phoneCodeStrLists[position]
              //  spDaySelection = dayEnd31List[position]
            }
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

        img_eye_confirm_password?.setOnClickListener {
            // etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            etConfirmPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
            img_eye_confirm_password_hide?.visibility = View.VISIBLE
            img_eye_confirm_password?.visibility = View.GONE
        }

        img_eye_confirm_password_hide?.setOnClickListener {
            //  etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            etConfirmPassword?.transformationMethod = HideReturnsTransformationMethod.getInstance()
            img_eye_confirm_password_hide?.visibility = View.GONE
            img_eye_confirm_password?.visibility = View.VISIBLE
        }
    }

    fun hasValidData() : Boolean{
        if (selectPhoneCode == null){
            Toast.makeText(this,"Please Select Phone Code",Toast.LENGTH_SHORT).show()
            return false
        }else if (etPhoneNo?.text.toString().isEmpty()){
            etPhoneNo?.error = "Enter Phone No"
            etPhoneNo?.requestFocus()
            return  false
        }
        else if (etEmail?.text.toString().isEmpty()) {
            etEmail?.error = "Enter Email"
            etEmail?.requestFocus()
            return  false
        }else if (etCode?.text.toString().isEmpty()){
            Toast.makeText(this,"You need to get Verify Code first!!", Toast.LENGTH_SHORT).show()
            return false
        } else if (etPassword?.text.toString().isEmpty()){
            etPassword?.error = "Enter Password"
            etPassword?.requestFocus()
            return false
        }else if (etConfirmPassword?.text.toString().isEmpty()){
            etConfirmPassword?.error = "Enter Confirm Password"
            etConfirmPassword?.requestFocus()
            return false
        }
        else if (etPassword?.text.toString().isNotEmpty() && etConfirmPassword?.text.toString().isNotEmpty()){
            if (etPassword?.text.toString() != etConfirmPassword?.text.toString()){
                etPassword?.error = "Password does not match"
                etPassword?.requestFocus()
                return false
            }
        }

        return  true
    }

    fun mockPhoneCode(){
        if (phoneCodeStrLists.isNotEmpty()){
            bindPhoneCodeAdapter()
        }
    }

    private fun bindPhoneCodeAdapter() {
        val aaPhoneCode = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            phoneCodeStrLists
        )
        aaPhoneCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPhoneCode?.adapter = aaPhoneCode
    }


    override fun instantiatePresenter(): RegisterPresenter {
        return RegisterPresenter(this, AppPrefHelper.getInstance(this))
    }

    override fun showRegisterSuccess(message: String) {
        runOnUiThread {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }
        startActivity(LoginActivity.newIntent(this))
        this.finish()
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
            Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
        }
    }
}