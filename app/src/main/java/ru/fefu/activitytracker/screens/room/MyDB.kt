package ru.fefu.activitytracker.screens.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.fefu.activitytracker.screens.room.calc.Converters


@Database(entities = [ActivityRoom::class], version = 4)
@TypeConverters(Converters::class)
abstract class MyDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}