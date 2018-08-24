package com.echannels.moismartservices.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/**
 * Created by N!K$ on 7/30/18.
 */
class PreferenceUtils private constructor(activity: Activity){

    private val SHARED_PREF_NAME = "MOI"
    private val USER_LOGIN_TOKEN = "login_token"

    private var mainActivity: Activity = activity
    private var prefs: SharedPreferences=mainActivity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private var prefEdit: SharedPreferences.Editor=prefs.edit()


    ///////////////////////
    //STATIC INITIALIZATION
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mInstance: PreferenceUtils? = null

        fun createInstance(activity: Activity) {
            mInstance = PreferenceUtils(activity)
        }

        fun getInstance(): PreferenceUtils {
            if (mInstance == null) {
                throw RuntimeException("Object has not been initialize for Preference Utils")
            }
            return mInstance as PreferenceUtils
        }
    }
    ///////////////////////END-Initialization



    /**
     * Save Logged in User Token
     */
    fun setLoggedInToken(loginToken: String) {
        try {
            prefs = mainActivity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            //val editor = prefs.edit()
            prefEdit.putString(USER_LOGIN_TOKEN, loginToken)
            prefEdit.apply()
            Log.e("Login Data: ", "Login Token has been Stored...")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /**
     * GET Logged in User Token
     */
    fun getLoggedInToken(): String {
        prefs = mainActivity.getSharedPreferences(SHARED_PREF_NAME, 0)
        return prefs.getString(USER_LOGIN_TOKEN, "")
    }


    fun flushLogginData() {
        prefs = mainActivity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        //val editor = prefs.edit()
        prefEdit.putString(USER_LOGIN_TOKEN, null)
        prefEdit.apply()
        Log.e("Login Data: ","Stored Login Token is REMOVED...")
    }


    /*fun getRefereshToken(): String {
        prefs = mainActivity.getSharedPreferences(SHARED_PREF_NAME, 0)
        return prefs.getString("refresh_token", "")
    }*/
}