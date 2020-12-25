package cn.edu.myplayer.util

import cn.edu.myplayer.R
import cn.edu.myplayer.base.BaseFragment
import cn.edu.myplayer.ui.fragment.HomeFragment
import cn.edu.myplayer.ui.fragment.MusicFragment

//管理fragment的util类
class FragmentUtil private constructor(){//私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val musicFragment by lazy { MusicFragment() }
    companion object {
        //惰性加载，只有用时加载
        val fragmentUtil by lazy { FragmentUtil() }
    }
    //根据tabId获取对应的fragment
    fun getFragment(tabId: Int): BaseFragment? {
        when(tabId) {
            R.id.tab_home-> return homeFragment
            R.id.tab_music-> return musicFragment
        }
        return null //basefragment不支持null 所以加上？
    }
}
