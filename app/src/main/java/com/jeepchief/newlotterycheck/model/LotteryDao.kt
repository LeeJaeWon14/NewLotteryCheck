package com.jeepchief.newlotterycheck.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LotteryDao {
    @Query("SELECT * FROM LotteryEntity")
    suspend fun selectLottery() : List<LotteryEntity>

    @Insert
    suspend fun insertLottery(entity: LotteryEntity)

    @Delete
    suspend fun deleteLottery(entity: LotteryEntity) : Int
}