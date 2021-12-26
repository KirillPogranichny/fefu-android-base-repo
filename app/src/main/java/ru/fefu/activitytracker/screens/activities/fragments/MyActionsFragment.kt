package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyBinding
import ru.fefu.activitytracker.screens.App
import ru.fefu.activitytracker.screens.activities.adapters.ListItemAdapter
import ru.fefu.activitytracker.screens.activities.cards.items.ListItem
import ru.fefu.activitytracker.screens.room.calc.toDateSeparator


class MyActionsFragment : StockFragment<FragmentMyBinding>(R.layout.fragment_my) {

    private val myListAdapter = ListItemAdapter()
    private val listData = mutableListOf<ListItem.MyCard>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.myRecyclerView){
            adapter = myListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        myListAdapter.setItemClickListener {
            val action = CollectionFragmentDirections.actionActivityMainFragmentToMyInfoFragment(listData[it].id)
            findNavController().navigate(action)
        }

        App.INSTANCE.db.activityDao().getAll().observe(viewLifecycleOwner) { activitiesList ->
            val activitiesMap = mutableMapOf<String, MutableList<ListItem.MyCard>>()

            activitiesList.forEach {
                it.finish?.let { finishTime ->
                    if (!activitiesMap.containsKey(finishTime.toDateSeparator())) {
                        activitiesMap[finishTime.toDateSeparator()] = mutableListOf()
                    }

                    activitiesMap[it.finish.toDateSeparator()]?.add(it.toMyCard())
                }
            }

            val packedList = mutableListOf<ListItem>()

            activitiesMap.forEach { (dateSeparatorContent, myActivitiesList) ->
                packedList.add(ListItem.DateCard(dateSeparatorContent))
                listData.add(ListItem.MyCard(-1, "1", "1", "1", "1", "1", "1"))
                myActivitiesList.forEach {
                    packedList.add(it)
                    listData.add(it)
                }

            }
            myListAdapter.submitList(packedList)
        }
    }
}