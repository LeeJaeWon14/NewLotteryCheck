package com.jeepchief.newlotterycheck.view.raffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.const.LottoConst
import com.jeepchief.newlotterycheck.databinding.LottoNumberBinding
import com.jeepchief.newlotterycheck.util.ColorChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class RaffleRowAdapter() : RecyclerView.Adapter<RaffleRowAdapter.RaffleRowViewHolder>() {
    class RaffleRowViewHolder(private val binding: LottoNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(num: Int) = CoroutineScope(Dispatchers.Main).launch {
            binding.tvRefDrwNumber.text = num.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaffleRowViewHolder {
        val binding = LottoNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RaffleRowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RaffleRowViewHolder, position: Int) {
        holder.bind(Random.nextInt(45))
    }

    fun change() {

    }

    override fun getItemCount(): Int = 6
}