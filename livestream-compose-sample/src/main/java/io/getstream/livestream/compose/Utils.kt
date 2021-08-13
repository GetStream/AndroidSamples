package io.getstream.livestream.compose

import android.content.Context
import android.content.res.TypedArray
import java.util.Random
import java.util.Calendar
import java.util.Date

fun Context.randomArtWork(): Int {
    val rand = Random()
    val artworksArray: TypedArray = resources.obtainTypedArray(R.array.artwork)
    val rndInt: Int = rand.nextInt(artworksArray.length())
    return artworksArray.getResourceId(rndInt, 0).also {
        artworksArray.recycle()
    }
}

fun Context.randomDescription(): Int {
    val rand = Random()
    val descArray: TypedArray = resources.obtainTypedArray(R.array.description)
    val rndInt: Int = rand.nextInt(descArray.length())
    return descArray.getResourceId(rndInt, 0).also {
        descArray.recycle()
    }
}

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun Context.getTimeAgo(date: Date): String {
    val now: Date = Calendar.getInstance().time
    val diff: Long = now.time - date.time
    return when {
        diff < SECOND_MILLIS -> {
            getString(R.string.just_now)
        }
        diff < MINUTE_MILLIS -> {
            String.format(getString(R.string.seconds_ago), diff / SECOND_MILLIS)
        }
        diff < 2 * MINUTE_MILLIS -> {
            String.format(getString(R.string.minutes_ago), 1)
        }
        diff < 59 * MINUTE_MILLIS -> {
            String.format(getString(R.string.minutes_ago), diff / MINUTE_MILLIS)
        }
        diff < 90 * MINUTE_MILLIS -> {
            String.format(getString(R.string.hour_ago), diff / HOUR_MILLIS)
        }
        diff < 48 * HOUR_MILLIS -> {
            getString(R.string.yesterday)
        }
        diff < 6 * DAY_MILLIS -> {
            String.format(getString(R.string.hour_ago), diff / DAY_MILLIS)
        }
        diff <8* DAY_MILLIS ->{
            getString(R.string.week_ago)
        }
        else -> {
            getString(R.string.long_time)
        }
    }
}
