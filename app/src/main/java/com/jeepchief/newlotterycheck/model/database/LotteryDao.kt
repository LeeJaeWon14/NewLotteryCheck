package com.jeepchief.newlotterycheck.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LotteryDao {
    @Query("SELECT * FROM LotteryEntity")
    fun selectLottery() : List<LotteryEntity>

    @Insert
    fun insertLottery(entity: LotteryEntity)

    @Delete
    fun deleteLottery(entity: LotteryEntity) : Int
}