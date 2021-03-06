package cn.edu.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WatchViewModel :ViewModel() {
//    私有 外面看不见
    private var _seconds:MutableLiveData<Int> = MutableLiveData()
    private var running = false
//    共有 外面可以看见
    val seconds:LiveData<Int> = _seconds

    init {
        runTimer()
    }

    fun start() {
        running = true
    }

    fun stop() {
        running = false
    }

    fun restart() {
        running = true
        _seconds.value = 0
    }

    fun runTimer() {
        val handler = Handler()
        val runnable = object: Runnable {
            override fun run() {
                if (running){
                    val sec = _seconds.value ?: 0
                    _seconds.value = sec + 1
                }
                handler.postDelayed(this,1000)
            }
        }
        handler.post (runnable)
    }
}