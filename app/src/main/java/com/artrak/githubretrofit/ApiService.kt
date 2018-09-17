package com.artrak.githubretrofit

import io.reactivex.Observable
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import java.net.CookieManager

interface ApiService {

    @retrofit2.http.GET("user")
    fun user(): Observable<User>


    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .client(OkHttpClient.Builder()
                            // this line is the important one:
                            .cookieJar(JavaNetCookieJar(CookieManager()))
                            .build())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}