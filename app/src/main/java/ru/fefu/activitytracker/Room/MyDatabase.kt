package ru.fefu.activitytracker.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ActivityRoom::class], version = 1)
@TypeConverters(Converter::class)
abstract class MyDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}