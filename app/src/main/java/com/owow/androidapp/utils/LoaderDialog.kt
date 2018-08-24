package com.echannels.moismartservices.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import com.owow.androidapp.R


/**
 * LOADER DIALOG
 */
class LoaderDialog {

    //private var mainActivity: Activity = activity
    private var dialogLoader: Dialog? = null

    ///////////////////////
    //STATIC INITIALIZATION
    companion object {

        private var mInstance: LoaderDialog = LoaderDialog()

        /*fun createInstance(activity: Activity) {
            mInstance = LoaderDialog(activity)
        }*/

        @Synchronized
        fun getInstance(): LoaderDialog {
            return mInstance
        }
    }
    ///////////////////////END-Initialization



    /*fun showLoader(){
        loaderDialog = Dialog(mainActivity)
        loaderDialog!!.setContentView(R.layout.custom_loader_dialog_layout)
        loaderDialog!!.setCancelable(false)
        loaderDialog!!.setCanceledOnTouchOutside(false)
        loaderDialog!!.window.attributes.windowAnimations = R.style.LoaderDiaogAnim
        loaderDialog!!.window.setBackgroundDrawableResource(android.R.color.transparent)
        loaderDialog!!.show()
    }

    fun hideLoader(){
        if (loaderDialog != null && loaderDialog!!.isShowing) {
            loaderDialog!!.hide()
            loaderDialog!!.dismiss()
        }
    }*/

    fun showLoader(activity: Activity){
        //if (dialogLoader == null) {
            dialogLoader = Dialog(activity)
            dialogLoader!!.setContentView(R.layout.custom_loader_dialog_layout)
            dialogLoader!!.setCancelable(false)
            dialogLoader!!.setCanceledOnTouchOutside(false)
            dialogLoader!!.window.attributes.windowAnimations = R.style.LoaderDiaogAnim
            dialogLoader!!.window.setBackgroundDrawableResource(android.R.color.transparent)
        //}
        dialogLoader!!.show()
    }

    fun hideLoader(){
        if (dialogLoader != null && dialogLoader!!.isShowing) {
            dialogLoader!!.hide()
            dialogLoader!!.dismiss()
            dialogLoader = null
        }
    }
}