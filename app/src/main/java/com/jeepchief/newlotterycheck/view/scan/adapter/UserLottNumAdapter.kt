package com.jeepchief.newlotterycheck.view.scan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.databinding.UserLottNumberBinding
import com.jeepchief.newlotterycheck.model.database.LotteryEntity

class UserLottNumAdapter(private val userLottList: List<List<Int>>, private val refNumbers: List<Int>) : RecyclerView.Adapter<UserLottNumAdapter.UserLottNumViewHolder>() {
    class UserLottNumViewHolder(private val binding: UserLottNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(numList: List<Int>, refNumbers: List<Int>) {
            binding.rvUserLottList.apply {
                layoutManager = LinearLayoutManager(this.context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = LottNumberAdapter(
                    *numList.toIntArray(),
                    refNumbers = refNumbers
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserLottNumViewHolder {
        val binding = UserLottNumberBinding.inflate(LayoutInflater.from(parent.context))
        return UserLottNumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserLottNumViewHolder, position: Int) {
        holder.bind(userLottList[position], refNumbers)
    }

    override fun getItemCount(): Int = userLottList.size
}