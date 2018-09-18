package com.artrak.githubretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getProjectsForuser("kavoronkov")
        getUser("kavoronkov")
    }

    private fun getUser(userName: String){
        val repository = SearchRepositoryProvider.provideSearchRepository()
        repository.getUser(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    var r = result.login
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun getProjectsForuser(userName: String){
        val repository = SearchRepositoryProvider.provideSearchRepository()
        repository.getReposForuser(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    var r = result[0].name
                }, { error ->
                    error.printStackTrace()
                })
    }
}
