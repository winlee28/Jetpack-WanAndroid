package com.win.ft_home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_home.model.home.Banner
import com.win.lib_image_loader.app.ImageLoaderManager
import com.youth.banner.adapter.BannerAdapter

class HomeBannerAdapter(mDatas: List<Banner>)//设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
    : BannerAdapter<Banner, HomeBannerAdapter.BannerViewHolder>(mDatas) {

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, data: Banner, position: Int, size: Int) {
        ImageLoaderManager.instance.displayImageForView(holder.imageView, data.imagePath)
    }

    inner class BannerViewHolder(var imageView: ImageView) :
        RecyclerView.ViewHolder(imageView)
}