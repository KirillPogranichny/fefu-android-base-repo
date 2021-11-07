package ru.fefu.activitytracker.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.fefu.activitytracker.databinding.MainPageBinding
import ru.fefu.activitytracker.screens.login.LoginScreenActivity
import ru.fefu.activitytracker.screens.registration.RegistrationScreenActivity

class MainScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: MainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = MainPageBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickRegistrationButton(view: View){
        bindingClass.registrationButton.setOnClickListener{
            val intent = Intent(this, RegistrationScreenActivity::class.java)
            startActivity(intent)
        }
    }

    fun onClickLoginButton(view: View){
        bindingClass.loginButton.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }
}