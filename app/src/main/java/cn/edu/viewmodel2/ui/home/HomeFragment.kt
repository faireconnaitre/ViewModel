package cn.edu.viewmodel2.ui.home

import cn.edu.viewmodel2.ui.home.HomeViewModel
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.edu.viewmodel2.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        homeViewModel =
//            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        //        持有viewmodel的引用
        homeViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(homeViewModel::class.java)
        homeViewModel.cities.observe(viewLifecycleOwner, Observer {
            val cities = it
            val _this = this
            //适配器
            val adapter =
                activity?.let { it1 -> ArrayAdapter(it1, android.R.layout.simple_list_item_1, cities) }
            listView.adapter = adapter
            //点击城市后获取城市
            listView.setOnItemClickListener { _, _, position, _ ->
                val cityCode = cities[position].city_code
                //构造意图 打开新的界面
                val intent = Intent(activity, MainActivity2::class.java)
                //往意图中传参
                intent.putExtra("city_code", cityCode)
                //启动意图
                startActivity(intent)
            }
        })
    }
}