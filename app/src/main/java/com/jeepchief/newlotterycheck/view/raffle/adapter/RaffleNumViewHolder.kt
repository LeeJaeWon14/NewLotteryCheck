package com.jeepchief.newlotterycheck.view.raffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.databinding.UserLottNumberBinding

class RaffleNumAdapter() : RecyclerView.Adapter<RaffleNumAdapter.RaffleNumViewHolder>() {
    private lateinit var binding: UserLottNumberBinding
    private val resultList = mutableListOf<Int>()
    class RaffleNumViewHolder(private val binding: UserLottNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(numList: List<Int>, refNumbers: List<Int>, scanResult: (Int) -> Unit) {
            binding.rvUserLottList.apply {
                layoutManager = LinearLayoutManager(this.context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = RaffleRowAdapter()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaffleNumViewHolder {
        val binding = UserLottNumberBinding.inflate(LayoutInflater.from(parent.context))
        return RaffleNumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RaffleNumViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 5
}