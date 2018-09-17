package com.artrak.githubretrofit

import io.reactivex.Observable

class SearchRepository(val apiService: ApiService) {

    fun getUser(): Observable<User>{
        return apiService.user()
    }
}