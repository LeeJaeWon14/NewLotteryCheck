package com.jeepchief.newlotterycheck.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class LotteryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "returnValue")
    val returnValue: String,

    @ColumnInfo(name = "drwNoDate")
    val drwNoDate: String,

    @ColumnInfo(name = "drwNo")
    val drwNo: Int,

    @ColumnInfo(name = "firstPrzwnerCo")
    val firstprzwnerco: Int,

    @ColumnInfo(name = "bnusNo")
    val bnusNo: Int,

    @ColumnInfo(name = "firstWinamnt")
    val firstWinAmnt: Int,

    @ColumnInfo(name = "drwtNo1")
    val drwtNo1: Int,

    @ColumnInfo(name = "drwtNo2")
    val drwtNo2: Int,

    @ColumnInfo(name = "drwtNo3")
    val drwtNo3: Int,

    @ColumnInfo(name = "drwtNo4")
    val drwtNo4: Int,

    @ColumnInfo(name = "drwtNo5")
    val drwtNo5: Int,

    @ColumnInfo(name = "drwtNo6")
    val drwtNo6: Int
)