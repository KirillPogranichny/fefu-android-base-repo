package ru.fefu.activitytracker.screens.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ActivityDao {
    @Query("SELECT * FROM my_database ORDER BY finish_time DESC")
    fun getAll(): LiveData<List<ActivityRoom>>

    @Insert
    fun insert(activity: ActivityRoom)

    @Delete
    fun delete(activity: ActivityRoom)
}