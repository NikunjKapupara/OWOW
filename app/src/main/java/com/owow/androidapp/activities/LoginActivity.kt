package com.owow.androidapp.activities

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import com.echannels.moismartservices.retrofit.RetrofitApiService
import com.echannels.moismartservices.utils.DialogUtils
import com.echannels.moismartservices.utils.LoaderDialog
import com.echannels.moismartservices.utils.LogUtils
import com.echannels.moismartservices.utils.PreferenceUtils
import com.google.gson.JsonObject
import com.owow.androidapp.R
import com.owow.androidapp.utils.NetworkUtils
import com.owow.androidapp.utils.ValidationUtils
import com.owow.androidapp.ws_models.LoginModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar_homescreen.*
import org.json.JSONObject
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    //// for temp. storing user login Token
    companion object {
        var loginToken:String=""
    }


    /**
     * Create Instance of the Retrofit API
     */
    private val RetrofitAPI by lazy {
        RetrofitApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginToken = ""
        logoutBTN.visibility = GONE
        toolbarTitleTV.text = getString(R.string.screen_title_login)

        loginBTN.setOnClickListener {
            if(areFieldsValidated())
                doUserLogin()
        }
    }


    /**
     * Fields validations
     */
    private fun areFieldsValidated():Boolean{
        if (ValidationUtils.isStringEmpty(userEmailET.text.toString())){
            DialogUtils.showAlertDialogOnly(this, getString(R.string.error_enter_email_address))
            return false
        }


        /**
         * can't check becoz use email address is "ioscase" so I skipped
         */
        /*if (!ValidationUtils.isEmailAddressCorrect(userEmailET.text.toString())){
            DialogUtils.showAlertDialogOnly(this, getString(R.string.error_invaid_email_format))
            return false
        }*/

        if (ValidationUtils.isStringEmpty(passwordET.text.toString())){
            DialogUtils.showAlertDialogOnly(this, getString(R.string.error_enter_password))
            return false
        }

        if(!NetworkUtils.isInternetAvailable(this)){
            DialogUtils.showAlertDialogOnly(this, getString(R.string.nointernet))
            return false
        }

        return true
    }


    /**
     * Do User Login Operation via WEb service API call
     */
    private fun doUserLogin() {
        LoaderDialog.getInstance().showLoader(this)
        val loginJsonObj = JsonObject()
        loginJsonObj.addProperty("email", userEmailET.text.toString())
        loginJsonObj.addProperty("password", passwordET.text.toString())

        /*loginJsonObj.addProperty("email", "ioscase")
        loginJsonObj.addProperty("password", "iOSCase")*/

        disposable = RetrofitAPI.userLogin(loginJsonObj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            LogUtils.getInstance().logError("SUCCESS: ", "result found")
                            navigateToHome(result)
                        },
                        { error ->
                            LoaderDialog.getInstance().hideLoader()
                            var message = "An error occurred"
                            if (error is HttpException){
                                // Kotlin will smart cast at this point
                                val errorJsonString = error.response().errorBody()?.string()
                                val errorObj = JSONObject(errorJsonString)
                                val errorMsg = errorObj.getJSONObject("errors").get("login")
                                message = errorMsg.toString()
                                LogUtils.getInstance().logError("ERROR: ", message)

                            }else {
                                message = error.message ?: message
                            }
                            //DialogUtils.showAlertDialogOnly(this, getString(R.string.login_auth_failed_msg))
                            DialogUtils.showAlertDialogOnly(this, message)
                        }
                )

    }


    /**
     * On Web service Success: Navigate user on Home Screen
     */
    private fun navigateToHome(loginResponse: LoginModel){
        try {
            //LogUtils.getInstance().logError("TOKEN: ", loginResponse.apiToken)
            LoaderDialog.getInstance().hideLoader()
            loginToken = loginResponse.apiToken
            userEmailET.setText("")
            passwordET.setText("")
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }
        catch (e:Exception){
            e.stackTrace
        }
    }


    /**
     * Clear Disposable Web Api call
     */
    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
