package ru.fefu.activitytracker.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.activitytracker.databinding.FragmentUsersBinding


class FragmentUsers : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newFragment() = FragmentUsers()
    }
}