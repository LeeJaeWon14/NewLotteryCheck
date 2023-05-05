package com.jeepchief.newlotterycheck.model.rest

import retrofit2.http.GET
import retrofit2.http.Query

interface LotteryService {
    @GET("common.do")
    suspend fun getLotteryInfo(
        @Query("method") methodName: String = "getLottoNumber",
        @Query("drwNo") drwNo: String
    ) : LotteryDTO
}