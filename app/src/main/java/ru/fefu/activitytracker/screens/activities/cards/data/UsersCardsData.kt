package ru.fefu.activitytracker.screens.activities.cards.data

import ru.fefu.activitytracker.screens.activities.cards.items.DateItem
import ru.fefu.activitytracker.screens.activities.cards.items.UserCardItem


class UsersCardsData {
    private val cardslist = listOf(
        DateItem(
            "Вчера"
        ),
        UserCardItem(
            "14.32 км",
            "@van_darkholme",
            "2 часа 46 минут",
            "Сёрфинг",
            "14 часов назад"
        ),
        UserCardItem(
            "228 м",
            "@techniquepasha",
            "14 часов 48 минут",
            "Качели",
            "14 часов назад"
        ),
        UserCardItem(
            "10 км",
            "@morgen_shtern",
            "1 час 10 минут",
            "Езда на кадилак",
            "14 часов назад"
        )
    )

    fun getUsersCards(): List<Any> = cardslist
}