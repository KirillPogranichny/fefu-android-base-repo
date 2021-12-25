package ru.fefu.activitytracker.screens.map.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityTrackFragmentBinding
import ru.fefu.activitytracker.screens.activities.fragments.StockFragment


class TrackFragment : StockFragment<ActivityTrackFragmentBinding>(R.layout.activity_track_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.finishButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}