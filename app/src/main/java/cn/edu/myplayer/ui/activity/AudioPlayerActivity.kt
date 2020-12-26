package cn.edu.myplayer.ui.activity

import cn.edu.myplayer.R
import cn.edu.myplayer.base.BaseActivity
import cn.edu.myplayer.model.AudioBean

class AudioPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initData() {
        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
        val position = intent.getIntExtra("position",-1)
        println("list${list} position${position}")
    }
}