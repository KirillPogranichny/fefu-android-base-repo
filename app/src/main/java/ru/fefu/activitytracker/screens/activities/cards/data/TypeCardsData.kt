package ru.fefu.activitytracker.screens.activities.cards.data

import ru.fefu.activitytracker.screens.activities.cards.items.TypeCard


class TypeCardsData {
    private val cardslist = listOf(
        TypeCard(
            "Велосипед",
            false
        ),
        TypeCard(
            "Бег",
            false
        ),
        TypeCard(
            "Почилить",
            false
        )
    )

    fun getTypeCards(): List<Any> = cardslist
}