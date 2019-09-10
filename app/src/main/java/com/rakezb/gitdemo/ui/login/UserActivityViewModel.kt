package com.rakezb.gitdemo.ui.login

import android.app.Application
import com.rakezb.gitdemo.MyApplication
import com.rakezb.gitdemo.core.BaseViewModel

class UserActivityViewModel(app: Application) : BaseViewModel(app) {

    init {
        (app as? MyApplication)?.component?.inject(this)
    }
}