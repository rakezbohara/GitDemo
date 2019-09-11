package com.rakezb.gitdemo.ui.home.adapter

import android.app.Application
import androidx.databinding.ObservableField
import com.rakezb.gitdemo.core.BaseViewModel
import com.rakezb.gitdemo.data.GithubUser

class UserItemViewModel(app: Application): BaseViewModel(app) {

    val githubUser: ObservableField<GithubUser> = ObservableField()

    fun setHistoryData(githubUser: GithubUser){
        this.githubUser.set(githubUser)
    }
}