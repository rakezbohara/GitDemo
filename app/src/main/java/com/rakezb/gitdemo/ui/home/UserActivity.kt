package com.rakezb.gitdemo.ui.home

import com.rakezb.gitdemo.R
import com.rakezb.gitdemo.core.BaseActivity
import com.rakezb.gitdemo.databinding.ActivityLoginBinding
import com.rakezb.gitdemo.ui.home.adapter.UserListAdapter
import com.rakezb.gitdemo.utils.extensions.afterTextChanged
import com.rakezb.gitdemo.utils.extensions.isNotEmptyorNull
import kotlinx.android.synthetic.main.toolbar_home.view.*

class UserActivity : BaseActivity<UserActivityViewModel, ActivityLoginBinding>(UserActivityViewModel::class.java) {

    override fun getLayoutRes() = R.layout.activity_login

    override fun initViewModel(viewModel: UserActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun initView() {
        super.initView()
        binding.setLifecycleOwner(this)
        viewModel.requestUserList()
        binding.recyclerView.adapter = UserListAdapter {
            //click listener for list items
        }
        binding.toolbarLayout.toolbarSearchEditText.afterTextChanged { newString ->
            if (newString.isNullOrEmpty()) {
                //edit text is empty
                viewModel.userList.postValue(viewModel.userListResponse.value)
            } else {
                //edit text has certain value
                viewModel.userList.postValue(viewModel.userListResponse.value?.filter { it ->
                    it?.login!!.contains(newString!!, true)
                })
            }


        }
    }
}