package ru.fefu.activitytracker.screens.activities.cards.data

import ru.fefu.activitytracker.screens.activities.cards.items.DateItem
import ru.fefu.activitytracker.screens.activities.cards.items.MyCardItem


class MyCardsData {
    private val cardslist = listOf(
        DateItem(
            "Вчера"
        ),
        MyCardItem(
            "14.32 км",
            "2 часа 46 минут",
            "Сёрфинг",
            "14 часов назад"
        ),
        DateItem(
            "Май 2022 года"
        ),
        MyCardItem(
            "1000 м",
            "60 минут",
            "Велосипед",
            "29.05.2022"
        ),
    )

    fun getMyCards(): List<Any> = cardslist
}