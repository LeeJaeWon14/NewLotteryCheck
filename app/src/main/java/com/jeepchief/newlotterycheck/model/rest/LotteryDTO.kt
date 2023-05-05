package com.jeepchief.newlotterycheck.model.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LotteryDTO(
    @Expose
    @SerializedName("returnValue")
    val returnvalue: String,
    @Expose
    @SerializedName("drwNoDate")
    val drwnodate: String,
    @Expose
    @SerializedName("drwtNo6")
    val drwtno6: Int,
    @Expose
    @SerializedName("drwtNo4")
    val drwtno4: Int,
    @Expose
    @SerializedName("firstPrzwnerCo")
    val firstprzwnerco: Int,
    @Expose
    @SerializedName("drwtNo5")
    val drwtno5: Int,
    @Expose
    @SerializedName("bnusNo")
    val bnusno: Int,
    @Expose
    @SerializedName("firstWinamnt")
    val firstWinamnt: Int
)