package cn.edu.myplayer.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import cn.edu.myplayer.R
import cn.edu.myplayer.base.BaseActivity
import cn.edu.myplayer.service.AudioService
import cn.edu.myplayer.service.Iservice
import kotlinx.android.synthetic.main.activity_music_player_bottom.*

class AudioPlayerActivity: BaseActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.state->updatePlayState()
        }
    }

    //更新播放状态
    private fun updatePlayState() {
        //更新播放状态
        iService?.updatePlayState()
        //更新播放状态图标
        updatePlayStateBtn()
    }

    //根据播放状态更新图标
    private fun updatePlayStateBtn() {
        //获取当前播放状态
        val isPlaying = iService?.isPlaying()

        isPlaying.let {
            //根据状态更新图标
            if (isPlaying!!) {
                //播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
            }else {
                //暂停
                state.setImageResource(R.drawable.selector_btn_audio_pause)
            }
        }
    }

    override fun initListener() {
        //播放状态切换
        state.setOnClickListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val conn by lazy { AudioConnetion() }

    override fun initData() {
//        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position",-1)

        //通过audioservice开启服务 播放音乐
        val intent = intent
        //修改
        intent.setClass(this, AudioService::class.java)
        //通过intent将list和position传过去
//        intent.putExtra("list", list)
//        intent.putExtra("position", position)
        //先开启
        startService(intent)
        //再绑定
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
//        //播放音乐
//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener {
//            //开始播放
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list?.get(position)?.data)
//        mediaPlayer.prepareAsync()
    }

    var iService:Iservice? = null
    inner class AudioConnetion:ServiceConnection {
        //service连接时
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            iService = p1 as Iservice
        }

        //service意外断开时
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(conn)
    }


}