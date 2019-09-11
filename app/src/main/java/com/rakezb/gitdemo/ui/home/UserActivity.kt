package com.rakezb.gitdemo.ui.home

import com.rakezb.gitdemo.R
import com.rakezb.gitdemo.core.BaseActivity
import com.rakezb.gitdemo.databinding.ActivityLoginBinding
import com.rakezb.gitdemo.ui.home.adapter.UserListAdapter

class UserActivity : BaseActivity<UserActivityViewModel, ActivityLoginBinding>(UserActivityViewModel::class.java) {

    override fun getLayoutRes() = R.layout.activity_login

    override fun initViewModel(viewModel: UserActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()
        binding.setLifecycleOwner(this)
        binding.recyclerView.adapter = UserListAdapter {

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUserList()
    }
}