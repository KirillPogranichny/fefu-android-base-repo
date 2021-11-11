package ru.fefu.activitytracker.screens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityPageBinding

class ActivitiesScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingClass = ActivityPageBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


    }
}