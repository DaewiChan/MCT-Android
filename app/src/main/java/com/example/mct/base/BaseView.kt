package com.example.mct.base

import android.content.Context

interface BaseView {
    /**
     * Returns the Context in which the application is running.
     * @return the Context in which the application is running
     */
//    fun getContext(): Context

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()


    fun showSuccess(message: String)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

}