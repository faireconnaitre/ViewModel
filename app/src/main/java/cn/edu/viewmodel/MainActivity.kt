package cn.edu.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var seconds = 0
    var running = false
    var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("life cycle","onCreate")

//        保存状态
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")

        }

        runTimer()

        button_start.setOnClickListener {
            running = true
        }
        button_stop.setOnClickListener {
            running = false
        }
        button_restart.setOnClickListener {
            running = true
            seconds = 0
        }

    }
    fun runTimer() {
        val handler = Handler()
        val runnable = object: Runnable {
            override fun run() {
                val hours = seconds /3600
                val minutes = (seconds %3600) /60
                val secs = seconds %60
                textView_time.text = String.format("%02d:%02d:%02d",hours, minutes,secs)
                if (running){
                    seconds ++
                }
                handler.postDelayed(this,1000)
            }
        }
        handler.post (runnable)
    }

    //    保存状态
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("life cycle","onSaveInstanceState")

        outState.putInt("seconds",seconds)
        outState.putBoolean("running",running)
        outState.putBoolean("wasRunning",wasRunning)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life cycle","onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life cycle","onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life cycle","onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life cycle","onResume")
        if (wasRunning) {
            running = true
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("life cycle","onPause")
        wasRunning = running
        running = false
    }
}