package com.example.mct.presentation.account.register

import com.example.mct.base.BasePresenter
import com.example.mct.base.BaseView
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.data.remote.ApiFactory
import com.example.mct.data.remote.request.GetVerifyCodeRequest
import com.example.mct.data.remote.request.RegisterByEmailRequest
import com.example.mct.data.vo.GetVerifyCodeDataVO
import com.example.mct.utilities.JSONUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

interface RegisterView: BaseView{
    fun showRegisterSuccess(message: String)
    fun showVerifyCode(data: GetVerifyCodeDataVO)
}

class RegisterPresenter(registerView: RegisterView, private val spAppPrefHelper: AppPrefHelper): BasePresenter<RegisterView>(registerView) {

    private val loginApi = ApiFactory.userApi

    fun userRegister(email: String, password: String, code: String, phoneCode: String, phone: String) {
        val register = RegisterByEmailRequest(email, code, password, phone, phoneCode)
        view.showLoading()
        loginApi.userRegisterByEmail(register)
           // .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { response ->
                   // if (response.code == 0) {
                       view.showRegisterSuccess(response.message ?: "")
                  //  }
                },
                { error ->
                    if (error is HttpException) {
                        val errorJsonString = error.response()!!.errorBody()?.string()
                        val errMessage = JSONUtil().parseError(errorJsonString!!)
                        view.showError(errMessage)

                    } else {
                        val errorMessage = error.message ?: ""
                        view.showError(errorMessage)
                    }
                }
            )
    }

    fun getVerifyCode(email: String) {
        val getCodeReq = GetVerifyCodeRequest(email, "reg","email")
        view.showLoading()
        loginApi.getVerifyCode(getCodeReq)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { response ->
                    //  if (response.code == 0) {
                    response.verifyCodeData.let {
                        view.showVerifyCode(it ?: GetVerifyCodeDataVO())
                    }
                    //  }
                },
                { error ->
                    if (error is HttpException) {
                        val errorJsonString = error.response()!!.errorBody()?.string()
                        val errMessage = JSONUtil().parseError(errorJsonString!!)
                        view.showError(errMessage)

                    } else {
                        val errorMessage = error.message ?: ""
                        view.showError(errorMessage)
                    }
                }
            )
    }
}