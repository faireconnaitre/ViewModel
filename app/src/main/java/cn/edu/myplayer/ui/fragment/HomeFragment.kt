package cn.edu.myplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.edu.myplayer.R
import cn.edu.myplayer.adapter.HomeAdapter
import cn.edu.myplayer.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment() {

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        //初始化recycleView
        recycleView.layoutManager = LinearLayoutManager(context)
        //适配
        val adapter = HomeAdapter()
        recycleView.adapter = adapter
    }
}