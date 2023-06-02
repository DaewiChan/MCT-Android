package com.example.mct.presentation.account.login

import com.example.mct.base.BasePresenter
import com.example.mct.base.BaseView
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.data.remote.ApiFactory
import com.example.mct.data.remote.request.GetVerifyCodeRequest
import com.example.mct.data.remote.request.LoginByEmailRequest
import com.example.mct.data.vo.GetVerifyCodeDataVO
import com.example.mct.utilities.JSONUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

interface LoginView: BaseView{
    fun showHome(message: String)
    fun showVerifyCode(data: GetVerifyCodeDataVO)
}

class LoginPresenter(loginView: LoginView, private val spAppPrefHelper: AppPrefHelper): BasePresenter<LoginView>(loginView) {

    private val loginApi = ApiFactory.userApi

    fun userLogin(email: String, password: String, verifyCode: String, sCode: String, userType: String ) {
        val login = LoginByEmailRequest(email, password,verifyCode,sCode)
        view.showLoading()
        loginApi.userLoginByEmail(login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { response ->
                  //  if (response.code == 0) {
                        view.showHome(response.message ?: "")
                        spAppPrefHelper.putBool(AppPrefHelper.IS_LOGIN, true)
                        spAppPrefHelper.putStringValue(AppPrefHelper.USER_LOGIN_TOKEN, response.loginData?.token)
                        spAppPrefHelper?.putStringValue(AppPrefHelper.USER_TYPE,userType)
                   // }
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
        val getCodeReq = GetVerifyCodeRequest(email, "login","email")
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