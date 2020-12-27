package com.example.syn.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.example.syn.R
import com.example.syn.base.BaseActivity

import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity: BaseActivity(), ViewPropertyAnimatorListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(200)
    }

    override fun onAnimationStart(view: View?) {

    }

    override fun onAnimationEnd(view: View?) {
        //进入主界面
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {

    }
}