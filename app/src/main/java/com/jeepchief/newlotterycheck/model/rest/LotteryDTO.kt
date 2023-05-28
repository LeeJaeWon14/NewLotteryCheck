package com.jeepchief.newlotterycheck.model.rest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LotteryDTO(
    @Expose
    @SerializedName("returnValue")
    val returnValue: String,

    @Expose
    @SerializedName("drwNoDate")
    val drwNoDate: String,

    @Expose
    @SerializedName("drwNo")
    val drwNo: Int,

    @Expose
    @SerializedName("firstPrzwnerCo")
    val firstprzwnerco: Int,

    @Expose
    @SerializedName("bnusNo")
    val bnusNo: Int,

    @Expose
    @SerializedName("firstWinamnt")
    val firstWinAmnt: Long,

    @Expose
    @SerializedName("drwtNo1")
    val drwtNo1: Int,

    @Expose
    @SerializedName("drwtNo2")
    val drwtNo2: Int,

    @Expose
    @SerializedName("drwtNo3")
    val drwtNo3: Int,

    @Expose
    @SerializedName("drwtNo4")
    val drwtNo4: Int,

    @Expose
    @SerializedName("drwtNo5")
    val drwtNo5: Int,

    @Expose
    @SerializedName("drwtNo6")
    val drwtNo6: Int
)