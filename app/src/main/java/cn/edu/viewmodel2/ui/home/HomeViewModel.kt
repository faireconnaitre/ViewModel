package cn.edu.viewmodel2.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.edu.viewmodel2.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.concurrent.thread

class HomeViewModel(application: Application) : AndroidViewModel(application) {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text


    //    可以改变 初始没有
    private val _cities:MutableLiveData<List<City>> = MutableLiveData()
    //    对外开放 可以被观察的数据
    val cities: LiveData<List<City>> = _cities

    init {
        thread {
            val str = readFileFromRaw(R.raw.citycode)
            val gson = Gson()
            val CityType = object : TypeToken<List<City>>() {}.type
            var cts: List<City> = gson.fromJson(str, CityType)
            cts = cts.filter { it.city_code != "" }
//            主线程用value更新 子线程用postValue更新
            _cities.postValue(cts)

        }
    }
    fun readFileFromRaw(rawName: Int): String? {
        try {
            val inputReader = InputStreamReader(getApplication<Application>().resources.openRawResource(rawName))
            val bufReader = BufferedReader(inputReader)
            var line: String? = ""
            var result: String? = ""
            while (bufReader.readLine().also({ line = it }) != null) {
                result += line
            }
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}