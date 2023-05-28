package com.jeepchief.newlotterycheck.util

import android.graphics.Color
import androidx.core.content.ContextCompat

object ColorChecker {
    fun convertColor(drwNum: Int) : Int {
        return when(drwNum) {
            in 1 .. 10 -> Color.parseColor("#e4a816")
            in 11 .. 20 -> Color.parseColor("#1994da")
            in 21 .. 30 -> Color.parseColor("#e86352")
            in 31 .. 40 -> Color.parseColor("#8f8f8f")
            in 41 .. 45 -> Color.parseColor("#5bb544")
            else -> -1
        }
    }
}