package com.rakezb.gitdemo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.rakezb.gitdemo.MyApplication
import com.rakezb.gitdemo.R
import com.rakezb.gitdemo.core.BaseAdapter
import com.rakezb.gitdemo.data.GithubUser
import com.rakezb.gitdemo.databinding.RowUserItemBinding


class UserListAdapter(private val clickCallback: ((GithubUser?) -> Unit)?) :
    BaseAdapter<GithubUser>(object : DiffUtil.ItemCallback<GithubUser>() {

        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = UserItemViewModel(parent.context.applicationContext as MyApplication)
        val binding = DataBindingUtil.inflate<RowUserItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_user_item,
            parent,
            false
        )
        binding.viewModel = viewModel
        binding.root.setOnClickListener {
            binding.viewModel?.let { clickCallback?.invoke(it.githubUser.get()) }
        }
        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as RowUserItemBinding).viewModel?.setHistoryData(getItem(position))
        binding.executePendingBindings()
    }
}