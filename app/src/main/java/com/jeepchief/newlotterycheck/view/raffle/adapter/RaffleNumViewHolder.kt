package com.jeepchief.newlotterycheck.view.raffle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.databinding.UserLottNumberBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class RaffleNumAdapter() : RecyclerView.Adapter<RaffleNumAdapter.RaffleNumViewHolder>() {
    class RaffleNumViewHolder(private val binding: UserLottNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(numList: List<Int>) = CoroutineScope(Dispatchers.Main).launch {
            binding.rvUserLottList.apply {
                layoutManager = LinearLayoutManager(this.context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = RaffleRowAdapter(numList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaffleNumViewHolder {
        val binding = UserLottNumberBinding.inflate(LayoutInflater.from(parent.context))
        return RaffleNumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RaffleNumViewHolder, position: Int) {
        holder.bind(makeRandomList())
    }

    private fun makeRandomList() : List<Int> {
        val prevNumList = mutableListOf<Int>()
        while(true) {
            if(prevNumList.size == 6) break
            val num = Random.nextInt(45)
            if(prevNumList.contains(num)) continue
            else {
                prevNumList.add(num)
            }
        }

        return prevNumList
    }
    private var isStopRaffle = false
    fun startRaffle() {
        isStopRaffle = false
        while(!isStopRaffle) {
            for(i in 0 until itemCount) {
                notifyItemChanged(i)
            }
        }
    }
    fun stopRaffle() {
        isStopRaffle = true
    }

    override fun getItemCount(): Int = 5
}