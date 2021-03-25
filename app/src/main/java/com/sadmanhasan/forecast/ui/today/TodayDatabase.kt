package com.sadmanhasan.forecast.ui.today

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodayEntity::class], version = 1, exportSchema = false)
abstract class TodayDatabase : RoomDatabase() {

    abstract fun todayDao(): TodayDao

    companion object {
        @Volatile
        private var instance: TodayDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { todayDataBase ->
                instance = todayDataBase
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TodayDatabase::class.java,
            "todayDatabase"
        ).fallbackToDestructiveMigration().build()
    }
}