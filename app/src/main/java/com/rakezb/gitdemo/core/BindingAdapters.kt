package com.rakezb.gitdemo.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:set_list")
    fun setList(recyclerView: RecyclerView?, list: List<Nothing>?) {
        val adapter: BaseAdapter<*>? = recyclerView?.adapter as BaseAdapter<*>?
        list?.let {
            adapter?.submitList(null)
            adapter?.submitList(list)
        }
    }

    @JvmStatic
    @BindingAdapter("app:setImgResource")
    fun setImgResource(imageView: ImageView?, resourceId: Int?) {
        if (resourceId != null)
            imageView?.setImageResource(resourceId)
        else imageView?.setImageResource(0)
    }

}




