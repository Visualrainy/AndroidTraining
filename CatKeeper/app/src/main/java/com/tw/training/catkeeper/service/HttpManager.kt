package com.tw.training.catkeeper.service

import com.tw.training.catkeeper.domain.CatsNearbyResponse
import rx.Subscriber

/**
 * Created by pchen on 16/11/2017.
 */
interface HttpManager {
    fun getNearbyCats(callback: Subscriber<CatsNearbyResponse>)
}