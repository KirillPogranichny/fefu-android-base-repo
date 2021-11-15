package ru.fefu.activitytracker.screens.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activitytracker.screens.fragments.FragmentMy
import ru.fefu.activitytracker.screens.fragments.FragmentUsers


class FragmentAdapter(frag : Fragment) : FragmentStateAdapter(frag) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            FragmentMy()
        else
            FragmentUsers()
    }
}