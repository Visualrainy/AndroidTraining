package com.tw.training.catkeeper.service

import com.google.gson.GsonBuilder
import com.tw.training.catkeeper.domain.CatsNearbyResponse
import com.tw.training.catkeeper.presenter.CatsNearbyPresenter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by pchen on 16/11/2017.
 */
interface CatService {

    @GET("catnip/moment/")
    fun getNearbyCats(): Observable<CatsNearbyResponse>

    companion object {
        fun getCatService(): CatService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                            .setDateFormat(CatsNearbyPresenter.DATE_FORMAT).create()))
                    .baseUrl("http://10.0.2.2:8080")
                    .build()
            return retrofit.create(CatService::class.java)
        }
    }
}