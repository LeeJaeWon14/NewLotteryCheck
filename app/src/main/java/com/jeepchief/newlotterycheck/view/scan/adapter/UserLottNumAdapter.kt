package com.jeepchief.newlotterycheck.view.scan.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.databinding.UserLottNumberBinding
import com.jeepchief.newlotterycheck.model.database.LotteryEntity
import com.jeepchief.newlotterycheck.util.Log
import kotlinx.coroutines.delay

class UserLottNumAdapter(
    private val userLottList: List<List<Int>>,
    private val refNumbers: List<Int>,
    private val scanResult: (Int) -> Unit
) : RecyclerView.Adapter<UserLottNumAdapter.UserLottNumViewHolder>() {
    private val resultList = mutableListOf<Int>()
    class UserLottNumViewHolder(private val binding: UserLottNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(numList: List<Int>, refNumbers: List<Int>, scanResult: (Int) -> Unit) {
            binding.rvUserLottList.apply {
                layoutManager = LinearLayoutManager(this.context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = LottNumberAdapter(
                    *numList.toIntArray(),
                    refNumbers = refNumbers
                ) { scanResult.invoke(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserLottNumViewHolder {
        val binding = UserLottNumberBinding.inflate(LayoutInflater.from(parent.context))
        return UserLottNumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserLottNumViewHolder, position: Int) {
        holder.bind(userLottList[position], refNumbers) { resultList.add(it) }
        if(position == itemCount -1){
            Handler(Looper.getMainLooper()).postDelayed({
                resultList.sortDescending()
                Log.e("resultList is $resultList")
                scanResult.invoke(resultList.first())
            }, 500)
        }
    }

    override fun getItemCount(): Int = userLottList.size
}