package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Build
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.myRecyclerView){
            adapter = myListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        myListAdapter.setItemClickListener {
            val action = CollectionFragmentDirections.actionActivityMainFragmentToMyInfoFragment()
            findNavController().navigate(action)
        }

        App.INSTANCE.db.activityDao().getAll().observe(viewLifecycleOwner) {
                activitiesList -> val dataList = mutableMapOf<String, MutableList<ListItem.MyCard>>()

            activitiesList.forEach {
                if (!dataList.containsKey(it.finish.toDateSeparator())) {
                    dataList[it.finish.toDateSeparator()] = mutableListOf()
                }
                dataList[it.finish.toDateSeparator()]?.add(it.toMyCard())
            }

            val packedList = mutableListOf<ListItem>()

            dataList.forEach {
                    (dateSeparatorContent, myCardsList) ->
                packedList.add(ListItem.DateCard(dateSeparatorContent))
                myCardsList.forEach {
                    packedList.add(it)
                }
            }

            myListAdapter.submitList(packedList)
        }
    }
}