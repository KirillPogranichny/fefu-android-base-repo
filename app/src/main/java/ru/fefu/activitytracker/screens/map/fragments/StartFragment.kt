package ru.fefu.activitytracker.screens.map.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityStartFragmentBinding
import ru.fefu.activitytracker.screens.App
import ru.fefu.activitytracker.screens.activities.fragments.StockFragment
import ru.fefu.activitytracker.screens.map.adapters.TypeListAdapter
import ru.fefu.activitytracker.screens.map.cards.items.TypeCard
import ru.fefu.activitytracker.screens.map.cards.items.TypeCardName
import ru.fefu.activitytracker.screens.room.ActivityRoom
import java.time.LocalDateTime
import ru.fefu.activitytracker.screens.map.MapActivity


class StartFragment : StockFragment<ActivityStartFragmentBinding>(R.layout.activity_start_fragment) {

    private var selectedActivity: TypeCardName? = null
    private val data = listOf(
        TypeCard(TypeCardName.BICYCLE.ordinal, false),
        TypeCard(TypeCardName.RUN.ordinal, false),
        TypeCard(TypeCardName.WALK.ordinal, false)
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if ((activity as MapActivity).activityId != -1) {
            val navController = findNavController()
            navController.navigate(
                StartFragmentDirections.actionActivityStartFragmentToActivityTrackFragment(
                    (activity as MapActivity).activityId,
                    true
                )
            )
        }

        val adapter = TypeListAdapter(data)
        binding.typeRecyclerView.adapter = adapter

        adapter.setItemClickListener { position, activityType ->
            selectedActivity = activityType
        }

        binding.startButton.setOnClickListener {
            selectedActivity?.let {
                val activityId = App.INSTANCE.db.activityDao().insert(
                    ActivityRoom(
                        0,
                        LocalDateTime.now(),
                        null,
                        selectedActivity!!,
                        listOf()
                    )
                ).toInt()
                val direction =
                    StartFragmentDirections.actionActivityStartFragmentToActivityTrackFragment(
                        activityId,
                        false
                    )
                findNavController().navigate(direction)
            }
        }
    }
}