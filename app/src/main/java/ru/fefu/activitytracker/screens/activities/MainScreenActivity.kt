package ru.fefu.activitytracker.screens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.screens.activities.fragments.FragmentAction
import ru.fefu.activitytracker.screens.activities.fragments.FragmentProfile


class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragmentContainer, FragmentAction(), "Action")
                commit()
            }
        }

        val btnNav = findViewById<BottomNavigationView>(R.id.botNavBar)
        btnNav.setOnItemSelectedListener {
            val activity = supportFragmentManager.findFragmentByTag("Action")
            val profile = supportFragmentManager.findFragmentByTag("Profile")

            if (it.itemId == R.id.action_activity) {
                if (activity !== null) {
                    supportFragmentManager.beginTransaction().show(activity).commit()
                }
                if (profile !== null) {
                    supportFragmentManager.beginTransaction().hide(profile).commit()
                }
            } else if (it.itemId == R.id.action_profile) {

                if (activity !== null) {
                    supportFragmentManager.beginTransaction().hide(activity).commit()
                }
                if (profile !== null) {
                    supportFragmentManager.beginTransaction().show(profile).commit()

                } else {
                    supportFragmentManager.beginTransaction().apply {
                        add(R.id.fragmentContainer, FragmentProfile(), "Profile")
                        commit()
                    }
                }
            }
            true
        }
    }
}