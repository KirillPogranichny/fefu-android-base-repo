package ru.fefu.activitytracker.screens.room.calc

import android.location.Location


fun List<Pair<Double, Double>>.getDistance(): Double {
    var result = 0.0f

    for(i in 1 until size) {
        val loc1 = locationOfCoords(get(i - 1).first, get(i - 1).second)
        val loc2 = locationOfCoords(get(i).first, get(i).second)
        val distance = loc1.distanceTo(loc2)
        result += distance
    }
    return result.toDouble()
}

fun Pair<Double, Double>.distanceTo(other: Pair<Double, Double>): Double {
    val loc1 = locationOfCoords(first, second)
    val loc2 = locationOfCoords(other.first, other.second)

    return loc1.distanceTo(loc2).toDouble()
}

fun Double.toFormattedDistance(): String {
    val ceilDistance = toInt()
    val km = ceilDistance / 1000
    val m = ceilDistance % 1000

    return when {
        km > 0 && m > 0 -> "$km км $m м"
        km > 0 -> "$km км"
        m >= 0 -> "$m м"
        else -> throw(IllegalStateException("Distance must be positive"))
    }
}

fun locationOfCoords(longitude: Double, latitude: Double): Location {
    val location = Location("something")
    location.longitude = longitude
    location.latitude = latitude

    return location
}