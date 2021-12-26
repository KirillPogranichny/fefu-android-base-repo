package ru.fefu.activitytracker.screens.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.*
import ru.fefu.activitytracker.screens.activities.cards.items.ListItem
import java.lang.IllegalArgumentException


class ListItemAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(ListItemCallback()) {

    private var itemClickListener: (Int) -> Unit = {}

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    fun getItemClickListener(): (Int) -> Unit {
        return itemClickListener
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is ListItem.DateCard -> R.layout.date_separator
            is ListItem.MyCard -> R.layout.my_actions_card
            is ListItem.UserCard -> R.layout.users_actions_card
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.date_separator -> DateCardViewHolder(
                DateSeparatorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.my_actions_card -> MyCardViewHolder(
                MyActionsCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.users_actions_card -> UserCardViewHolder(
                UsersActionsCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DateCardViewHolder -> holder.bind(currentList[position] as ListItem.DateCard)
            is MyCardViewHolder -> holder.bind(currentList[position] as ListItem.MyCard)
            is UserCardViewHolder -> holder.bind(currentList[position] as ListItem.UserCard)
        }
    }

    inner class DateCardViewHolder(private val binding: DateSeparatorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dateCard: ListItem.DateCard) {
            binding.tvDateSeparator.text = dateCard.date
        }
    }

    inner class MyCardViewHolder(private val binding: MyActionsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.myCardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    itemClickListener.invoke(position)
            }
        }

        fun bind(myCard: ListItem.MyCard) {
            binding.activityDistance.text = myCard.distance
            binding.activityDuration.text = myCard.duration
            binding.activityType.text = myCard.type
            binding.activityStartTime.text = myCard.start_time
        }
    }

    inner class UserCardViewHolder(private val binding: UsersActionsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    itemClickListener.invoke(position)
            }
        }

        fun bind(userCard: ListItem.UserCard) {
            binding.activityDistance.text = userCard.distance
            binding.activityUsername.text = userCard.username
            binding.activityDuration.text = userCard.duration
            binding.activityType.text = userCard.type
            binding.activityStartTime.text = userCard.start_time
        }
    }

    internal class ListItemCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.DateCard && newItem is ListItem.DateCard ->
                    oldItem.date == newItem.date
                oldItem is ListItem.MyCard && newItem is ListItem.MyCard ->
                    oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.DateCard && newItem is ListItem.DateCard ->
                    areItemsTheSame(oldItem, newItem)
                oldItem is ListItem.MyCard && newItem is ListItem.MyCard ->
                    oldItem ==newItem
                else -> false
            }
        }
    }
}