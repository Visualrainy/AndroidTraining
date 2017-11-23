package com.tw.training.catkeeper.service

import com.tw.training.catkeeper.domain.CatsNearbyResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by pchen on 16/11/2017.
 */
class HttpManagerImpl: HttpManager {
    override fun getNearbyCats(callback: Subscriber<CatsNearbyResponse>) {
        CatService.getCatService().getNearbyCats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
    }

}