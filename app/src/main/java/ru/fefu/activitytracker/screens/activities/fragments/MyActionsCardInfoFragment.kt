package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.MyActionsCardInfoBinding
import ru.fefu.activitytracker.screens.App
import ru.fefu.activitytracker.screens.room.calc.getDistance
import ru.fefu.activitytracker.screens.room.calc.toFinishDateOrTime
import ru.fefu.activitytracker.screens.room.calc.toFormattedDurationBetween
import ru.fefu.activitytracker.screens.room.calc.toTime
import java.time.Duration


class MyActionsCardInfoFragment : StockFragment<MyActionsCardInfoBinding>(R.layout.my_actions_card_info) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val id = requireArguments().get("id") as Int
        val data = App.INSTANCE.db.activityDao().getById(id)

        binding.myInfoToolbar.title = data.type.type
        binding.distanceInfo.text = data.coords.getDistance().toString()
        binding.pastTimeInfo.text = data.start.toFinishDateOrTime()
        binding.durationInfo.text = Duration.between(data.start, data.finish).toFormattedDurationBetween()
        binding.startTimeInfo.text = data.start.toTime()
        binding.finishTimeInfo.text = data.finish?.toTime()
        binding.myInfoToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}