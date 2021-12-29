package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityStartFragmentBinding
import ru.fefu.activitytracker.screens.activities.adapters.TypeListAdapter
import ru.fefu.activitytracker.screens.activities.cards.data.TypeCardsData
import ru.fefu.activitytracker.screens.activities.cards.items.TypeCard


class StartFragment : StockFragment<ActivityStartFragmentBinding>(R.layout.activity_start_fragment) {
    private val repo = TypeCardsData()
    private val typeListAdapter = TypeListAdapter(repo.getTypeCards() as List<TypeCard>)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.typeRecyclerView){
            adapter = typeListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.startButton.setOnClickListener {
            val action = StartFragmentDirections.actionActivityStartFragmentToActivityTrackFragment()
            findNavController().navigate(action)
        }
    }
}