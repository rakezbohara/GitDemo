package com.rakezb.gitdemo.ui.login

import com.rakezb.gitdemo.R
import com.rakezb.gitdemo.core.BaseActivity
import com.rakezb.gitdemo.databinding.ActivityLoginBinding

class UserActivity : BaseActivity<UserActivityViewModel, ActivityLoginBinding>(UserActivityViewModel::class.java) {

    override fun getLayoutRes() = R.layout.activity_login

    override fun initViewModel(viewModel: UserActivityViewModel) {
        binding.viewModel = viewModel
    }
}