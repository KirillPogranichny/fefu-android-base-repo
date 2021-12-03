package ru.fefu.activitytracker.screens.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.screens.activities.cards.items.CardItem
import ru.fefu.activitytracker.screens.activities.cards.items.DateItem
import ru.fefu.activitytracker.screens.activities.cards.items.UserCardItem


class UsersListAdapter (cards: List<CardItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val Cards = cards.toMutableList()
    private var cardClickListener: (Int) -> Unit = {}

    companion object {
        private const val ITEM_TYPE_ACTIVITY = 1
        private const val ITEM_TYPE_DATE = 2
    }

    fun setCardClickListener(listener: (Int) -> Unit) {
        cardClickListener = listener
    }

    override fun getItemCount(): Int = Cards.size

    override fun getItemViewType(position: Int): Int =
        if (Cards[position]::class == UserCardItem::class) {
            ITEM_TYPE_ACTIVITY
        } else {
            ITEM_TYPE_DATE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_ACTIVITY) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.users_actions_card, parent, false)
            UsersListViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.date_separator, parent, false)
            DateListViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_ACTIVITY) {
            (holder as UsersListViewHolder).bind(Cards[position] as UserCardItem)
        } else {
            (holder as DateListViewHolder).bind(Cards[position] as DateItem)
        }
    }

    inner class DateListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date: TextView = itemView.findViewById(R.id.tvDateSeparator)

        fun bind(dateCard: DateItem) {
            date.text = dateCard.date
        }
    }

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val distance: TextView = itemView.findViewById(R.id.activity_distance)
        private val username: TextView = itemView.findViewById(R.id.activity_username)
        private val duration: TextView = itemView.findViewById(R.id.activity_duration)
        private val type: TextView = itemView.findViewById(R.id.activity_type)
        private val startTime: TextView = itemView.findViewById(R.id.activity_start_time)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    cardClickListener.invoke(position)
            }
        }

        fun bind(userCard: UserCardItem) {
            distance.text = userCard.distance
            username.text = userCard.username
            duration.text = userCard.duration
            type.text = userCard.type
            startTime.text = userCard.start_time
        }
    }
}