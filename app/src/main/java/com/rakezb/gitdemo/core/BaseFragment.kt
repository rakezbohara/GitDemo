package com.rakezb.gitdemo.core

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rakezb.gitdemo.BR
import com.rakezb.gitdemo.R
import com.rakezb.gitdemo.utils.ActivityUtils
import org.jetbrains.anko.support.v4.toast

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    androidx.fragment.app.Fragment() {
    lateinit var viewModel: VM
    open lateinit var mBinding: DB
    lateinit var mContext: Context

    private var mProgressDialogMsg: String? = null
    private var mProgressDialog: ProgressDialog? = null

    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        mBinding.setLifecycleOwner(this)
    }

    open fun init() {}

    @LayoutRes
    abstract fun getLayoutRes(): Int

    private fun getViewM(): VM = ViewModelProviders.of(this).get(mViewModelClass)

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()

    }

    abstract fun initViewModel(viewModel: VM)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mContext = activity!!
        init(inflater, container!!)
        init()
        initViewModel(viewModel)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewM()
        setHasOptionsMenu(false)
        mBinding.setVariable(BR.viewModel, viewModel)
        mBinding?.executePendingBindings()
        baseObservers()
        mProgressDialogMsg = getString(R.string.please_wait)
        setupProgressDialog()
        viewStarter()
    }

    open fun viewStarter() {
        initView()
    }

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        if (visible && isResumed) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!userVisibleHint) {
            return
        }

        onFragmentVisible()
        //INSERT CUSTOM CODE HERE
    }

    open fun onFragmentVisible() {

    }

    open fun initView() {}

    open fun refresh() {}


    private fun baseObservers() {
        viewModel.errorMessage.observe(this, Observer {
            viewModel.toastMessage.postValue(it)
        })

        viewModel.toastMessage.observe(this, Observer {
            toast(it)
        })

        viewModel.progressDialogMessage.observe(this, Observer {
            mProgressDialogMsg = it
        })

        viewModel.loadingVisibility.observe(this, Observer {
            if (it == View.VISIBLE)
                showProgressDialog()
            else if (it == View.GONE)
                hideProgressDialog()
        })
    }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false, addToBackStack: Boolean = true) {
        ActivityUtils.changeFragment(activity as AppCompatActivity, fragment, cleanStack, addToBackStack)
    }

    fun addFragment(fragment: Fragment, cleanStack: Boolean = false, addToBackStack: Boolean = true) {
        ActivityUtils.addFragment(activity as AppCompatActivity, fragment, cleanStack, addToBackStack)
    }

    fun finish() {
        if (activity != null && activity?.isFinishing == false) {
            activity?.finish()
        }
    }

    private fun updateProgressDialogMessage(message: String) {
        mProgressDialog?.setMessage(message)
    }

    private fun setupProgressDialog() {
        mProgressDialog = ProgressDialog(context)
        mProgressDialog?.setMessage(mProgressDialogMsg)
        mProgressDialog?.setCancelable(false)
    }

    private fun showProgressDialog() {
        mProgressDialog?.show()
    }

    private fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }
}
