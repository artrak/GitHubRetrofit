package com.artrak.githubretrofit

object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(ApiService.create())
    }
}