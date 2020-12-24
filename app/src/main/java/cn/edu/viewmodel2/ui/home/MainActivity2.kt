package cn.edu.viewmodel2.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import cn.edu.viewmodel2.weather.Forecast
import cn.edu.viewmodel2.weather.Weather
import cn.edu.viewmodel2.R
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.listView

class MainActivity2 : AppCompatActivity() {
    //url
    val baseURL = " http://t.weather.itboy.net/api/weather/city/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //从意图中获取参数
        val cityCode = intent.getStringExtra("city_code")
        //访问网络,Volley自动启动多线程
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(baseURL+cityCode, {
            //回调在主线程中
            val gson = Gson()
            val WeatherType = object :TypeToken<Weather>() {}.type
            val weather = gson.fromJson<Weather>(it, WeatherType)
            textView_city.text = weather.cityInfo.city
            textView_province.text = weather.cityInfo.parent
            textView_shidu.text = weather.data.shidu
            textView_wendu.text = weather.data.wendu
            val firstDay = weather.data.forecast.first()
            when(firstDay.type) {
                "晴" -> imageView.setImageResource(R.drawable.sun)
                "阴" -> imageView.setImageResource(R.drawable.cloud)
                "多云" -> imageView.setImageResource(R.drawable.mcloud)
                "阵雨" -> imageView.setImageResource(R.drawable.rain)
                else -> imageView.setImageResource(R.drawable.thunder)
            }
            //适配器
            val adapter = ArrayAdapter<Forecast>(this,android.R.layout.simple_list_item_1,weather.data.forecast)
            listView.adapter = adapter

            //关注的信息
            Log.d("MainActivity2","${weather.cityInfo.city} ${weather.cityInfo.parent}")
        },{
            Log.d("MainActivity2","$it")
        })
        queue.add(stringRequest)
    }
}