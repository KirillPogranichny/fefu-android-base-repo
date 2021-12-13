package ru.fefu.activitytracker.screens.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.screens.activities.cards.items.CardItem
import ru.fefu.activitytracker.screens.activities.cards.items.DateItem
import ru.fefu.activitytracker.screens.activities.cards.items.MyCardItem


class MyListAdapter (cards: List<CardItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
        if (Cards[position]::class == MyCardItem::class) {
            ITEM_TYPE_ACTIVITY
        } else {
            ITEM_TYPE_DATE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_ACTIVITY) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.my_actions_card, parent, false)
            MyListViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.date_separator, parent, false)
            DateListViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_ACTIVITY) {
            (holder as MyListViewHolder).bind(Cards[position] as MyCardItem)
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

    inner class MyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val distance: TextView = itemView.findViewById(R.id.activity_distance)
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

        fun bind(myCard: MyCardItem) {
            distance.text = myCard.distance
            duration.text = myCard.duration
            type.text = myCard.type
            startTime.text = myCard.start_time
        }
    }
}