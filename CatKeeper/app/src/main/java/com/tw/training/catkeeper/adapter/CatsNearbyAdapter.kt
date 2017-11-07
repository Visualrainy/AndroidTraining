package com.tw.training.catkeeper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.network.Constants.Companion.HOST_DOMAIN
import com.tw.training.catkeeper.utils.loadImageUrl

/**
 * Created by pchen on 26/10/2017.
 */
class CatsNearbyAdapter(context: Context, var mCatsNearbyList: List<CatsNearby>?) :
        RecyclerView.Adapter<CatsNearbyAdapter.CatsNearbyViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    var mProfileClickListener: OnProfileClickListener? = null

    override fun getItemCount(): Int {
        return mCatsNearbyList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CatsNearbyAdapter.CatsNearbyViewHolder?, position: Int) {
        holder!!.mNameTv.text = mCatsNearbyList?.get(position)?.name
        holder.mAvatarIv.loadImageUrl(HOST_DOMAIN +
                mCatsNearbyList?.get(position)?.avatar?.imageUrl)
        holder.mUpdateTime.text = mCatsNearbyList!![position].updateTime.toString()
        holder.mDescription.text = mCatsNearbyList!![position].description
        for ((index, value) in mCatsNearbyList!![position].thumbsList.withIndex()) {
            holder.mThumbs[index].loadImageUrl(HOST_DOMAIN + value.imageUrl)
            holder.mThumbs[index].visibility = View.VISIBLE
        }
        for (i: Int in (mCatsNearbyList!![position].thumbsList.size..2)) {
            holder.mThumbs[i].visibility = View.INVISIBLE
        }
        holder.mPosition = position
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CatsNearbyAdapter.CatsNearbyViewHolder {
        val itemView = mInflater.inflate(R.layout.cats_nearby_item, parent, false)
        return CatsNearbyViewHolder(itemView)
    }

    inner class CatsNearbyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mPosition = 0
        val mNameTv: TextView = itemView.findViewById(R.id.cat_name)
        val mAvatarIv: ImageView = itemView.findViewById(R.id.cat_avatar)
        val mUpdateTime: TextView = itemView.findViewById(R.id.update_time)
        val mDescription: TextView = itemView.findViewById(R.id.cat_description)
        val mThumbs = listOf<ImageView>(itemView.findViewById(R.id.thumb_1),
                itemView.findViewById(R.id.thumb_2), itemView.findViewById(R.id.thumb_3))
//        val mThumbIv1: ImageView = itemView.findViewById(R.id.thumb_1)
//        val mThumbIv2: ImageView = itemView.findViewById(R.id.thumb_2)
//        val mThumbIv3: ImageView = itemView.findViewById(R.id.thumb_3)

        init {
            mAvatarIv.setOnClickListener {
                mProfileClickListener?.onProfileClick(mPosition)
            }
        }
    }

    interface OnProfileClickListener {
        fun onProfileClick(position: Int)
    }
}