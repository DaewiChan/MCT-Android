package com.example.mct.base

import android.content.SharedPreferences


abstract class BasePresenter<out V : BaseView>(protected val view: V) {




    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

}