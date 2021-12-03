package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentUsersBinding
import ru.fefu.activitytracker.screens.activities.adapters.UsersListAdapter
import ru.fefu.activitytracker.screens.activities.cards.data.UsersCardsData
import ru.fefu.activitytracker.screens.activities.cards.items.CardItem


class UsersActionsFragment : StockFragment<FragmentUsersBinding>(R.layout.fragment_users) {

    private val data = UsersCardsData()
    private val usersListAdapter = UsersListAdapter(data.getUsersCards() as List<CardItem>)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.usersRecyclerView){
            adapter = usersListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        usersListAdapter.setCardClickListener {
            val fragmentManager = parentFragment?.parentFragmentManager
            val currentFragment = fragmentManager?.findFragmentByTag("UserCardInfo")
            val activityFragment = fragmentManager?.findFragmentByTag("activity")
            fragmentManager?.beginTransaction()?.apply {
                if (currentFragment != null) {
                    detach(currentFragment)
                }
                add(
                    R.id.fragmentContainer,
                    UsersActionsCardInfoFragment(),
                    "UserCardInfo"
                )
                if (activityFragment != null){
                    detach(activityFragment)
                }
                addToBackStack("UserCardInfo")
                commit()
            }
        }
    }
}