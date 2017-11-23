package com.tw.training.catkeeper.service

/**
 * Created by pchen on 16/11/2017.
 */
class HttpManagerFactory {
    companion object {
        private val sInstance: HttpManager = HttpManagerImpl()

        fun getInstance(): HttpManager = sInstance
    }
}