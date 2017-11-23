package com.tw.training.catkeeper.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by pchen on 16/11/2017.
 */
data class CatsNearbyResponse(@SerializedName("moments")val moments: List<CatsNearby>)