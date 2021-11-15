package ru.fefu.activitytracker.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.activitytracker.databinding.FragmentMyBinding


class FragmentMy : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newFragment() = FragmentMy()
    }
}