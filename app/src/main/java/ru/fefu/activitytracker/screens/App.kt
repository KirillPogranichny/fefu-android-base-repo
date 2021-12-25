package ru.fefu.activitytracker.screens

import android.app.Application
import androidx.room.Room
import ru.fefu.activitytracker.screens.room.MyDatabase

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    val db: MyDatabase by lazy {
        Room.databaseBuilder(
                this,
                MyDatabase::class.java,
                "my_database"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}