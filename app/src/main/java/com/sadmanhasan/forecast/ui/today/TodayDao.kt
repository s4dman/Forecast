package com.sadmanhasan.forecast.ui.today

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodayDao {
    @Query("SELECT * from today_table")
    suspend fun getTodayFromDB(): TodayEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodayIntoDB(todayEntity: TodayEntity)
}