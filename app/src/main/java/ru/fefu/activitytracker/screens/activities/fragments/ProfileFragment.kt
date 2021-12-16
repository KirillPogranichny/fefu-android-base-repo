package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentProfileBinding


class ProfileFragment : StockFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changePasswordBtn.setOnClickListener {
            val action = ProfileFragmentDirections.actionActivityProfileFragmentToChangePasswordFragment()
            findNavController().navigate(action)
        }
    }
}