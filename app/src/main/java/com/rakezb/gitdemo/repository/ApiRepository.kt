package com.rakezb.gitdemo.repository

import com.rakezb.gitdemo.service.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject
constructor() {

    @Inject
    lateinit var api: ApiService

    fun getGithubUserList() = api.requestUserList()

}