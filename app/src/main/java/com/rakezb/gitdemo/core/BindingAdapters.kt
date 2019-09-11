package com.rakezb.gitdemo.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakezb.gitdemo.BuildConfig
import com.squareup.picasso.Picasso


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

    @JvmStatic
    @BindingAdapter("app:setImgResource")
    fun setImgResource(imageView: ImageView?, imageUrl: String?) {
        //val imageUrl = bankDetail.iconurl
        if (imageUrl?.isNullOrEmpty() == true)
            return
        /*PicassoTrustAll.getInstance(imageView?.context)
            .load(imageUrl)
            .into(imageView)*/
        val picasso = Picasso.Builder(imageView?.context)
            .listener { _, _, e ->
                if (BuildConfig.DEBUG)
                    e.printStackTrace()
            }
            .build()


        picasso.load(imageUrl)
            .into(imageView)

    }

}




