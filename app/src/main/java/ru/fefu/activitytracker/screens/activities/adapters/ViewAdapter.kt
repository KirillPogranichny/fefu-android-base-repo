package ru.fefu.activitytracker.screens.activities.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activitytracker.screens.activities.fragments.MyActionsFragment
import ru.fefu.activitytracker.screens.activities.fragments.UsersActionsFragment


class ViewAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MyActionsFragment()
        } else {
            UsersActionsFragment()
        }
    }
}