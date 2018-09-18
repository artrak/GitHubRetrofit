package com.artrak.githubretrofit

import io.reactivex.Observable

class SearchRepository(val apiService: ApiService) {

    fun getUser(userName: String): Observable<User>{
        return apiService.user(userName)
    }

    fun getReposForuser(user: String): Observable<List<GitHubRepo>> {
        return apiService.reposForuser(user)
    }

//    fun postLogin(login: LoggingIn): Observable<Result> {
//        return apiService.login(login)
//    }
//
//    fun getListPass(): Observable<PasswordsList>{
//        return apiService.passwords()
//    }
}