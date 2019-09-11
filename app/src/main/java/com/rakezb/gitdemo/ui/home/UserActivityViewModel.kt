package com.rakezb.gitdemo.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rakezb.gitdemo.MyApplication
import com.rakezb.gitdemo.core.BaseViewModel
import com.rakezb.gitdemo.data.GithubUser
import com.rakezb.gitdemo.repository.ApiRepository
import com.rakezb.gitdemo.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserActivityViewModel(app: Application) : BaseViewModel(app) {

    @Inject
    lateinit var apiRepository: ApiRepository

    init {
        MyApplication.component.inject(this)
    }

    var userList: MutableLiveData<List<GithubUser?>?> = MutableLiveData()

    fun requestUserList() {
        compositeDisposable.add(apiRepository.getGithubUserList()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { onApiRequestStart() }
            .doOnTerminate { onApiRequestFinish() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                userList.postValue(response)
            }, {
                onApiRequestError(it)
            })
        )
    }

}