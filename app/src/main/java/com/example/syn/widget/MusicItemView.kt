package com.example.syn.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.syn.R
import com.example.syn.model.AudioBean
import kotlinx.android.synthetic.main.item_music.view.*

class MusicItemView: RelativeLayout {


    //new时使用
    constructor(context: Context?) : super(context)
    //布局文件中使用
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    //主题相关使用
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_music, this)
    }
    //数据绑定view+data
    fun setData(itemBean: AudioBean?) {
        //歌曲名称
        title.text = itemBean?.display_name
        //歌手名
        artist.text = itemBean?.artist
        //歌曲大小
        size.text = itemBean?.size?.let { Formatter.formatFileSize(context, it) }
    }
}


