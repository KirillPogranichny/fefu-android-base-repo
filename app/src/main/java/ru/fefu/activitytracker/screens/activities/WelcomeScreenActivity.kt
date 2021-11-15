package ru.fefu.activitytracker.screens.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.fefu.activitytracker.databinding.ActivityWelcomeBinding


class WelcomeScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityWelcomeBinding.inflate(layoutInflater)
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