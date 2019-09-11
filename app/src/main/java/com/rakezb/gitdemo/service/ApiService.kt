package com.rakezb.gitdemo.service

import com.rakezb.gitdemo.data.GithubUser
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    fun requestUserList(): Observable<List<GithubUser?>?>
}