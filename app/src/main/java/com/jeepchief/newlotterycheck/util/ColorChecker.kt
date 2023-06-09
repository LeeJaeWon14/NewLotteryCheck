package com.jeepchief.newlotterycheck.util

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.jeepchief.newlotterycheck.R

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

    fun convertBackground(drwNum: Int) : Int {
        return when(drwNum) {
            in 1 .. 10 -> R.drawable.draw_number_1_to_10_background
            in 11 .. 20 -> R.drawable.draw_number_11_to_20_background
            in 21 .. 30 -> R.drawable.draw_number_21_to_30background
            in 31 .. 40 -> R.drawable.draw_number_31_to_40_background
            in 41 .. 45 -> R.drawable.draw_number_41_to_45_background
            else -> -1
        }
    }
}