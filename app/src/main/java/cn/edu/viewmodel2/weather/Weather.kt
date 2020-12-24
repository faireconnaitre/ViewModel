package cn.edu.viewmodel2.weather

import cn.edu.viewmodel2.weather.CityInfo
import cn.edu.viewmodel2.weather.Data

data class Weather(
    val cityInfo: CityInfo,
    val `data`: Data,
    val date: String,
    val message: String,
    val status: Int,
    val time: String
)