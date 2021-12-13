package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentMyBinding
import ru.fefu.activitytracker.screens.activities.adapters.MyListAdapter
import ru.fefu.activitytracker.screens.activities.cards.data.MyCardsData
import ru.fefu.activitytracker.screens.activities.cards.items.CardItem


class MyActionsFragment : StockFragment<FragmentMyBinding>(R.layout.fragment_my) {

    private val data = MyCardsData()
    private val myListAdapter = MyListAdapter(data.getMyCards() as List<CardItem>)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.myRecyclerView){
            adapter = myListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        myListAdapter.setCardClickListener {
            val fragmentManager = parentFragment?.parentFragmentManager
            val currentFragment = fragmentManager?.findFragmentByTag("MyCardInfo")
            val activityFragment = fragmentManager?.findFragmentByTag("activity")
            fragmentManager?.beginTransaction()?.apply {
                if (currentFragment != null) {
                    detach(currentFragment)
                }
                add(
                    R.id.fragmentContainer,
                    MyActionsCardInfoFragment(),
                    "MyCardInfo"
                )
                if (activityFragment != null) {
                    detach(activityFragment)
                }
                addToBackStack("MyCardInfo")
                commit()
            }
        }
    }
}