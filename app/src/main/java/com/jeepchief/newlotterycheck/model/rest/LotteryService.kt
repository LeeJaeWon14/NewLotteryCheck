package com.jeepchief.newlotterycheck.model.rest

import retrofit2.http.GET
import retrofit2.http.Query

interface LotteryService {
    @GET("common.do")
    fun getLotteryInfo(
        @Query("methodName") methodName: String = "getLottoNumber",
        @Query("drwNo") drwNo: String
    )
}