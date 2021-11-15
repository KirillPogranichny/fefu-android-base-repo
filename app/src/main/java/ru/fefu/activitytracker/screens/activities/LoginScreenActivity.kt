package ru.fefu.activitytracker.screens.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activitytracker.databinding.ActivityLoginBinding


class LoginScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.goBackButton.setOnClickListener {
            finish()
        }
    }
}