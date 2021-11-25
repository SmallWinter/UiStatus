package com.fengchen.uistatus.demo

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import com.fengchen.uistatus.listener.OnCompatRetryListener
import com.fengchen.uistatus.listener.OnRetryListener

/**
 * com.fengchen.uistatus.demo.BaseActivity
 * Created by cxd on 2021/11/25
 * Describe:
 */
abstract class BaseActivity : AppCompatActivity() {

    protected open var mUIStatusController: UiStatusController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initUIStatusManager()
    }

    protected abstract fun initView()

    private fun initUIStatusManager() {
        if (contentResId() > 0) {
            val statusView = this.findViewById<View>(contentResId())
            mUIStatusController = UiStatusController.get()
                .bind(statusView)
            mUIStatusController?.onCompatRetryListener =
                OnCompatRetryListener { uiStatus, target, controller, trigger ->
                    if(uiStatus != UiStatus.LOADING){
                        onRetry()
                    }
                }
        }
    }

    protected open fun contentResId(): Int = 0

    protected open fun onRetry(){}

    protected fun showCustomResId(
        @UiStatus status: Int,
        @LayoutRes customResId: Int,
        @IdRes retryTriggerViewId: Int = 0,
        listener: OnRetryListener? = null
    ) {
        if(listener != null && retryTriggerViewId != 0){
            mUIStatusController?.addUiStatusConfig(status, customResId , retryTriggerViewId, listener)
        }else{
            mUIStatusController?.addUiStatusConfig(status, customResId)
        }
        mUIStatusController?.changeUiStatus(status)
    }

    protected fun showLoading() {
        mUIStatusController?.changeUiStatus(UiStatus.LOADING)
    }

    protected fun showEmpty() {
        mUIStatusController?.changeUiStatus(UiStatus.EMPTY)
    }

    protected fun showError() {
        mUIStatusController?.changeUiStatus(UiStatus.LOAD_ERROR)
    }

    protected fun showContent() {
        mUIStatusController?.changeUiStatus(UiStatus.CONTENT)
    }

}