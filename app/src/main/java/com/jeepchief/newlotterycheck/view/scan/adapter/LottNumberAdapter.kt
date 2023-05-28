package com.jeepchief.newlotterycheck.view.scan.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.LottoNumberBinding
import com.jeepchief.newlotterycheck.util.ColorChecker

class LottNumberAdapter(vararg drwNumbers: Int, private val refNumbers: List<Int>) : RecyclerView.Adapter<LottNumberAdapter.LottNumberViewHolder>() {
    private val drwNumList: List<Int>
    init {
        drwNumList = drwNumbers.toList()
    }
    class LottNumberViewHolder(private val binding: LottoNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drwNumber: Int, isLast: Boolean, refNumbers: List<Int>) {
            binding.tvRefDrwNumber.apply {
                drwNumber.toString().also { num ->
                    text = num
                    refNumbers.forEach { refNum ->
                        if(refNum == num.toInt())
                            setTextColor(ColorChecker.convertColor(num.toInt()))
                    }
                }
                if(isLast)
                    setBackgroundColor(R.color.logo_color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LottNumberViewHolder {
        val binding = LottoNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LottNumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LottNumberViewHolder, position: Int) {
//        Log.e("number >> ${drwNumList[position]}")
        holder.bind(drwNumList[position], position == 6, refNumbers)
    }

    override fun getItemCount(): Int = drwNumList.size
}