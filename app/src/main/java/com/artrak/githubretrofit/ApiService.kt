package com.artrak.githubretrofit

import io.reactivex.Observable
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.http.Body
import retrofit2.http.Path
import java.net.CookieManager
import retrofit2.http.POST
import retrofit2.http.GET





interface ApiService {

    @retrofit2.http.GET("users/{user}")
    fun user(@Path("user") userName: String): Observable<User>

    @GET("/users/{user}/repos")
    fun reposForuser(@Path("user") user: String): Observable<List<GitHubRepo>>

    @POST("/users/new")
    fun createUser(@Body user: User): Observable<User>

//    @retrofit2.http.POST("login")
//    fun login(@Body login: LoggingIn): Observable<Result>

//    @retrofit2.http.GET("user/passwords")
//    fun passwords(): Observable<PasswordsList>


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