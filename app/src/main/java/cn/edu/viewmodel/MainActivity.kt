package cn.edu.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: WatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("life cycle","onCreate")
        //初始化viewmodel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(WatchViewModel::class.java)
        //添加观察器
        viewModel.seconds.observe(this, Observer {
            val hours = it /3600
            val minutes = (it %3600) /60
            val secs = it % 60
            textView_time.text = String.format("%02d:%02d:%02d",hours, minutes,secs)
        })


        button_start.setOnClickListener {
            viewModel.start()
        }
        button_stop.setOnClickListener {
            viewModel.stop()
        }
        button_restart.setOnClickListener {
            viewModel.restart()
        }

    }



}