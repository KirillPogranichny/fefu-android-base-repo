package ru.fefu.activitytracker.screens.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.MyActionsCardInfoBinding


class MyActionsCardInfoFragment : StockFragment<MyActionsCardInfoBinding>(R.layout.my_actions_card_info) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myInfoToolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}