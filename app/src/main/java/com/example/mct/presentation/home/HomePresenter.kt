package com.example.mct.presentation.home

import com.example.mct.base.BasePresenter
import com.example.mct.base.BaseView
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.data.remote.ApiFactory
import com.example.mct.data.remote.request.GetVerifyCodeRequest
import com.example.mct.data.remote.request.LoginByEmailRequest
import com.example.mct.data.vo.GetVerifyCodeDataVO
import com.example.mct.data.vo.LoginUserDataVO
import com.example.mct.utilities.JSONUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

interface HomeView: BaseView{
    fun showProfileData(data: LoginUserDataVO)
}

class HomePresenter(homeView: HomeView, private val spAppPrefHelper: AppPrefHelper): BasePresenter<HomeView>(homeView) {

    private val loginApi = ApiFactory.userApi

    fun getUserInfo() {
        val token = spAppPrefHelper.getStringValue(AppPrefHelper.USER_LOGIN_TOKEN)
        view.showLoading()
        loginApi.getUserInfo(token,"en")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { response ->
                  if (response.loginData != null){
                      view?.showProfileData(response.loginData?.loginUserData!!)
                  }
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