package ru.fefu.activitytracker.screens.activities.cards.items

data class MyCardItem (
    val distance : String,
    val duration : String,
    val type : String,
    val start_time : String
    ) : CardItem