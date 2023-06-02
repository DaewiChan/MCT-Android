package com.example.mct.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment <P : BasePresenter<BaseView>> : BaseView, DialogFragment() {
    protected lateinit var presenter: P
    public var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        presenter.onViewCreated()
        mProgressDialog = ProgressDialog(context)
    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window!!.attributes = params as WindowManager.LayoutParams
    }

    /**
     * Instantiates the presenter the Fragment is based on.
     */
    protected abstract fun instantiatePresenter(): P
}