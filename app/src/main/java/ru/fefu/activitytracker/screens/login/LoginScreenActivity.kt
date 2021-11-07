package ru.fefu.activitytracker.screens.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activitytracker.databinding.LoginPageBinding
import ru.fefu.activitytracker.databinding.MainPageBinding

class LoginScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: LoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = LoginPageBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.goBackButton.setOnClickListener {
            finish()
        }
    }
}