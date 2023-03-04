package com.jeepchief.newlotterycheck.view.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeepchief.newlotterycheck.view.raffle.RaffleFragment
import com.jeepchief.newlotterycheck.view.record.RecordFragment
import com.jeepchief.newlotterycheck.view.scan.ScanFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> { ScanFragment() }
            1 -> { RaffleFragment() }
            2 -> { RecordFragment() }
            else -> throw IllegalStateException("Not found fragment")
        }
    }
}