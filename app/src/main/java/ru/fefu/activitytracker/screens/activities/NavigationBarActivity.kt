package ru.fefu.activitytracker.screens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.screens.activities.fragments.CollectionFragment
import ru.fefu.activitytracker.screens.activities.fragments.ProfileFragment


class NavigationBarActivity : AppCompatActivity() {
    private var bottomNav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.botNavBar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add (
                    R.id.fragmentContainer,
                    CollectionFragment(),
                    "activity"
                )
                commit()
            }
        }
        navSelect()
    }

    private fun navSelect() {
        bottomNav?.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.action_activity -> {
                    val activityFragment = supportFragmentManager.findFragmentByTag("activity")
                    val profileFragment = supportFragmentManager.findFragmentByTag("profile")
                    supportFragmentManager.beginTransaction().apply{
                        if(activityFragment != null) {
                            attach(activityFragment)
                        }
                        else {
                            add(
                                R.id.fragmentContainer,
                                CollectionFragment(),
                                "activity"
                            )
                        }
                        if (profileFragment != null)
                            detach(profileFragment)
                        commit()
                    }
                    true
                }
                R.id.action_profile-> {
                    val activityFragment = supportFragmentManager.findFragmentByTag("activity")
                    val profileFragment = supportFragmentManager.findFragmentByTag("profile")
                    supportFragmentManager.beginTransaction().apply {
                        if(profileFragment != null) {
                            attach(profileFragment)
                        }
                        else {
                            add(
                                R.id.fragmentContainer,
                                ProfileFragment(),
                                "profile"
                            )
                        }
                        if (activityFragment != null)
                            detach(activityFragment)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }
}