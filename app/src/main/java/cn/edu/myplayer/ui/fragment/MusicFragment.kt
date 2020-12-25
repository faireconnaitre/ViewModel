package cn.edu.myplayer.ui.fragment

import android.view.View
import cn.edu.myplayer.R
import cn.edu.myplayer.base.BaseFragment

class MusicFragment: BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_music, null)
    }

    override fun initData() {
        //加载音乐列表数据

    }
}