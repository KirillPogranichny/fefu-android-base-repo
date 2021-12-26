package ru.fefu.activitytracker.screens.map.fragments

import android.os.Bundle
import android.view.View
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityTrackFragmentBinding
import ru.fefu.activitytracker.screens.activities.fragments.StockFragment
import kotlinx.coroutines.*
import org.osmdroid.util.GeoPoint
import ru.fefu.activitytracker.screens.App
import ru.fefu.activitytracker.screens.activities.ActivityService
import ru.fefu.activitytracker.screens.map.MapActivity
import ru.fefu.activitytracker.screens.room.ActivityFinishTimeUpdate
import ru.fefu.activitytracker.screens.room.ActivityRoom
import ru.fefu.activitytracker.screens.room.calc.toFormattedDistance
import ru.fefu.activitytracker.screens.room.calc.toTimerFormat
import java.time.Duration
import java.time.LocalDateTime
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import ru.fefu.activitytracker.screens.activities.NavigationBarActivity


class TrackFragment: StockFragment<ActivityTrackFragmentBinding>(R.layout.activity_track_fragment) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activityId = requireArguments()["activityId"] as Int
        var reassembled = requireArguments()["reassembled"] as Boolean
        ActivityService.startForegroundTracking(requireContext(), activityId)

        val activityData: ActivityRoom = App.INSTANCE.db.activityDao().getById(activityId)

        binding.typeTitle.text = activityData.type.type

        App.INSTANCE.db.activityDao().getLiveById(activityId)
            .observe(viewLifecycleOwner) {
                if (it.coords.isNotEmpty() && !reassembled) {
                    val lastCoordinate = it.coords.last()
                    (activity as MapActivity).polyline.addPoint(
                        GeoPoint(lastCoordinate.first, lastCoordinate.second)
                    )
                } else if (it.coords.isNotEmpty() && reassembled) {
                    it.coords.forEach {
                            coordinate ->
                        (activity as MapActivity).polyline.addPoint(
                            GeoPoint(coordinate.first, coordinate.second)
                        )
                    }
                    reassembled = false
                }
            }

        val uiJob = GlobalScope.launch {
            withContext(Dispatchers.IO) {
                while (true) {
                    activity?.runOnUiThread {
                        binding.durationTrack.text =
                            Duration.between(activityData.start, LocalDateTime.now()).toTimerFormat()

                        binding.distanceTrack.text = ActivityService.distance.toFormattedDistance()

                    }
                    delay(1000L)
                }
            }
        }

        binding.finishButton.setOnClickListener {
            ActivityService.stopForegroundTracking(requireActivity(), activityId)

            App.INSTANCE.db.activityDao().updateFinishTime(
                ActivityFinishTimeUpdate(ActivityService.activityId, LocalDateTime.now()))

            uiJob.cancel()
            val intent = Intent(requireContext(), NavigationBarActivity::class.java)
            startActivity(intent)
            (activity as MapActivity).finish()
            onDestroy()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}