package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityTabsFragmentBinding
import ru.fefu.activitytracker.screens.activities.adapters.ViewAdapter


class CollectionFragment : StockFragment<ActivityTabsFragmentBinding>(R.layout.activity_tabs_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewPager.adapter = ViewAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text =
                if (position == 0) {
                    "Мои"
                } else
                    "Пользователей"
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.activityButton.setOnClickListener() {
            val action = CollectionFragmentDirections.actionActivityMainFragmentToActivityMap()
            findNavController().navigate(action)
        }
    }
}