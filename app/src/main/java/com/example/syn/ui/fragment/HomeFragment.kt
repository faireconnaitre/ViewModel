package com.example.syn.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syn.R
import com.example.syn.adapter.HomeAdapter
import com.example.syn.base.BaseFragment
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