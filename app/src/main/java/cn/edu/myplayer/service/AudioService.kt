package cn.edu.myplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import cn.edu.myplayer.model.AudioBean

class AudioService:Service() {
    var mediaPlayer:MediaPlayer? = null
    var list:ArrayList<AudioBean>? = null
    var position: Int = 0
    val binder by lazy { AudioBinder() }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //获取集合以及position
        list = intent?.getParcelableArrayListExtra<AudioBean>("list")
        position = intent?.getIntExtra("position",-1) ?: -1//为 null获取不到值赋值为 -1
        //开始播放音乐
        binder.playItem()
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    inner class AudioBinder : Binder(), Iservice, MediaPlayer.OnPreparedListener {
        //启动播放器
        override fun onPrepared(mp: MediaPlayer?) {
            //播放音乐
            mediaPlayer?.start()
        }
        //播放音乐
        fun playItem() {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener {this}
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }
        }

        //更新播放状态
        override fun updatePlayState() {
            //获取当前播放状态
            val isPlaying = isPlaying()
            isPlaying.let {
                if (isPlaying!!) {
                    //播放 暂停
                    mediaPlayer?.pause()
                }else {
                    //暂停 播放
                    mediaPlayer?.start()
                }
            }
            //切换播放状态

        }
        override fun isPlaying():Boolean? {
            return mediaPlayer?.isPlaying
        }
    }
}