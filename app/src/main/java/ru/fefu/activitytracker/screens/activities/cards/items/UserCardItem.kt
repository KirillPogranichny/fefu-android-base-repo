package ru.fefu.activitytracker.screens.activities.cards.items

data class UserCardItem (
    val distance : String,
    val username : String,
    val duration : String,
    val type : String,
    val start_time : String
    ) : CardItem