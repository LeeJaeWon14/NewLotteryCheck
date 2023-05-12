package com.jeepchief.newlotterycheck.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LotteryEntity::class], version = 1, exportSchema = false)
abstract class LotteryRoomDatabase : RoomDatabase() {
    abstract fun getLotteryDao() : LotteryDao

    private var instance: LotteryRoomDatabase? = null
    fun getInstance(context: Context) : LotteryRoomDatabase {
        return instance ?: run {
            instance = Room.databaseBuilder(
                context.applicationContext,
                LotteryRoomDatabase::class.java,
                "LotteryDB.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            instance!!
        }
    }
}