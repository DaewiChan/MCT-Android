package com.example.mct.base

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mct.R
import java.util.*

abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    protected lateinit var presenter: P
    //public var mProgressDialog: ProgressDialog? = null
    var mProgressDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        presenter.onViewCreated()

        mProgressDialog = Dialog(this)
        val inflate = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null)
        mProgressDialog?.setContentView(inflate)
        mProgressDialog?.setCancelable(false)
        mProgressDialog?.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    fun showSessionTimeout( message: String, myCallback: (result: String?) -> Unit){
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok") { dialog, id ->
                myCallback.invoke("pressed ok")
            }
        // negative button text and action
//            .setNegativeButton("Cancel")
//            { dialog, id -> dialog.cancel()
//            }
        dialogBuilder.show()
    }
}