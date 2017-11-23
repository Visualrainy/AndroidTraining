package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.domain.CatsNearby

/**
 * Created by pchen on 27/10/2017.
 */
interface CatsNearbyContract {
    interface View {
        fun showNearbyCats(catsNearby: List<CatsNearby>?)
        fun onGetSuccess(data: List<CatsNearby>)
        fun onGetFailed(data: String)
    }
    interface Presenter {
        fun start()
        fun stop()
    }
}