package com.example.mct.base

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.mct.R

abstract class BaseFragment<P : BasePresenter<BaseView>> : BaseView, Fragment() {
    protected lateinit var presenter: P

    var mProgressDialog: Dialog? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        presenter.onViewCreated()
        //mProgressDialog = ProgressDialog(context)

        mProgressDialog = Dialog(requireContext())
        val inflate = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
        mProgressDialog?.setContentView(inflate)
        mProgressDialog?.setCancelable(false)
        mProgressDialog?.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }

    override fun onDestroy() {
        presenter.onViewDestroyed()
        super.onDestroy()
    }

    fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
    }

    fun checkStoragePermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2
            )
            return false
        }
        return true
    }

    fun showSessionTimeout(message: String, myCallback: (result: String?) -> Unit) {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok") { dialog, id ->
                myCallback.invoke("pressed ok")
//                AppPrefHelper.getInstance(this).clearPref()
//                showLogin()
//                presenter.deleteUserInfo()
            }
            // negative button text and action
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.cancel()
            }
        dialogBuilder.show()
    }


    /**
     * Instantiates the presenter the Fragment is based on.
     */
    protected abstract fun instantiatePresenter(): P

    //abstract fun showHome(data: Data?)
    abstract fun showHome()
}