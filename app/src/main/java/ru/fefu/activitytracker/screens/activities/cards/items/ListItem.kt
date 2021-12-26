package ru.fefu.activitytracker.screens.activities.cards.items


sealed class ListItem {
    class DateCard (
            val date: String
    ) : ListItem()

    class MyCard (
        val id: Int,
        val type : String,
        val distance : String,
        val duration : String,
        val finish_date: String,
        val start_time : String,
        val finish_time: String,
    ) : ListItem()

    class UserCard (
            val distance : String,
            val username : String,
            val duration : String,
            val type : String,
            val start_time : String
    ) : ListItem()
}
