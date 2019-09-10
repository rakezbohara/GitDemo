package com.rakezb.gitdemo.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager



class CommonUtils {

    companion object {
        fun hideKeyboard(activity: Activity?) {
            val view = activity?.currentFocus
            view?.let { v ->
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
            }
        }

        fun showKeyboard(activity: Activity) {
            val view = activity.currentFocus
            val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            assert(view != null)
            methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}