package com.echannels.moismartservices.retrofit

import com.echannels.moismartservices.utils.PreferenceUtils
import com.google.gson.JsonObject
import com.owow.androidapp.activities.LoginActivity
import com.owow.androidapp.ws_models.LoginModel
import com.owow.androidapp.ws_models.MatchesFeedsModel
import io.reactivex.Observable
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by N!K$ on 8/24/18.
 */
interface RetrofitApiService {

    /**
     * CREATE INSTANCE FOR API SERVICE
     */
    companion object {

        fun create(): RetrofitApiService {

            val httpClient = OkHttpClient.Builder()

            /// to print the App Log I added -> HttpLoggingInterceptor()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(interceptor)

            /// to add the web service request header
            httpClient.addInterceptor { chain ->
                /*val request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8").build()
                chain.proceed(request)*/

                val original = chain.request()
                val builder = original.newBuilder()
                builder.addHeader("Accept", "application/json")
                builder.addHeader("Content-Type", "application/json; charset=utf-8").build()

                if (LoginActivity.loginToken != null && LoginActivity.loginToken.isNotEmpty()){
                    builder.addHeader("Authorization", "Bearer "+LoginActivity.loginToken)
                }

                val request = builder.build()
                 chain.proceed(request)
            }
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(HttpConstants.BASE_URL)
                    .client(httpClient.build())
                    .build()

            return retrofit.create(RetrofitApiService::class.java)
        }
    }



                                        /**
                                         * OWOW WEB SERVICES
                                         */


    /**
     * DO USER LOGIN
      */
    @POST("auth/login")
    fun userLogin(@Body loginPostbody: JsonObject): Observable<LoginModel>


    /**
     * GET ALL MATCHES FEEDS
     */
    @GET("matches")
    fun getAllMatchesFeeds(@Query ("page") pageIndex:Int): Observable<MatchesFeedsModel>

}