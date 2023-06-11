package com.jeepchief.newlotterycheck.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class RefNumsEntity(
    @ColumnInfo(name = "drwNo")
    val drwNo: Int,

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