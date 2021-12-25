package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentUsersBinding
import ru.fefu.activitytracker.screens.activities.adapters.ListItemAdapter


class UsersActionsFragment : StockFragment<FragmentUsersBinding>(R.layout.fragment_users) {

    private val usersListAdapter = ListItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.usersRecyclerView){
            adapter = usersListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        usersListAdapter.setItemClickListener {
            val action = CollectionFragmentDirections.actionActivityMainFragmentToUserInfoFragment()
            findNavController().navigate(action)
        }
    }
}