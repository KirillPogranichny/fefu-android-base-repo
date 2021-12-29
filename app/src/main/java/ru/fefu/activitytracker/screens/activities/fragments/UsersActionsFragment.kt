package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
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
            val action = CollectionFragmentDirections.actionActivityMainFragmentToUserInfoFragment()
            findNavController().navigate(action)
        }
    }
}