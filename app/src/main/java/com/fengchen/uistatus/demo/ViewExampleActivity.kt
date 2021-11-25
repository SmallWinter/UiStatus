package com.fengchen.uistatus.demo

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.fengchen.uistatus.annotation.UiStatus

/**
 * Created by 段露 on 2019/02/01 16:12.
 *
 * @author 段露
 * @version 1.0.0
 * @class ViewExampleActivity
 * @describe View中使用示例.
 */
class ViewExampleActivity : BaseActivity() {
    override fun contentResId(): Int {
        return R.id.tv_example
    }

    override fun initView() {
        setContentView(R.layout.activity_example_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "View中使用示例"
        findViewById<View>(R.id.tv_example).setOnClickListener { v: View? ->
            showCustomResId(
                UiStatus.NETWORK_ERROR,
                R.layout.ui_status_my_custom
            )
        }
    }

    override fun onRetry() {
        super.onRetry()
        Toast.makeText(this , "啊啦啦啦" , Toast.LENGTH_SHORT).show()
        showContent()
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ showContent() }, 1000)
    }
}