package com.example.syn.ui.activity

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.syn.R
import com.example.syn.base.BaseActivity
import com.example.syn.util.FragmentUtil
import com.example.syn.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager{
    //惰性加载，只有用时加载
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        //设置tab切换监听
        bottomBar.setOnTabSelectListener {
//            it代表参数tabId
            val transaction = supportFragmentManager.beginTransaction()
            FragmentUtil.fragmentUtil.getFragment(it)?.let { it1 ->
                transaction.replace(R.id.container,
                    it1,it.toString())
            }

            transaction.commit()
        }
    }
}