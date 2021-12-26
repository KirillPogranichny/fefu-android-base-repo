package ru.fefu.activitytracker.screens.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.fefu.activitytracker.screens.activities.cards.items.ListItem
import ru.fefu.activitytracker.screens.map.cards.items.TypeCardName
import ru.fefu.activitytracker.screens.room.calc.*
import java.time.Duration
import java.time.LocalDateTime


@Entity(tableName = "my_database")
data class ActivityRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "start_time") val start: LocalDateTime,
    @ColumnInfo(name = "finish") val finish: LocalDateTime?,
    val type: TypeCardName,
    val coords: List<Pair<Double, Double>>
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun toMyCard(): ListItem.MyCard {
        return ListItem.MyCard(
            id,
            type.type,
            coords.getDistance().toFormattedDistance(),
            Duration.between(start, finish).toFormattedDurationBetween(),
            finish!!.toFinishDateOrTime(),
            start.toTime(),
            finish.toTime()
        )
    }
}

data class ActivityDistanceUpdate(
    val id: Int,
    val coords: List<Pair<Double, Double>>
)

data class ActivityFinishTimeUpdate(
    val id: Int,
    @ColumnInfo(name = "finish") val finish: LocalDateTime,
)