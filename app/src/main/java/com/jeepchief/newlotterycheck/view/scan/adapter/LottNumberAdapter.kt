package com.jeepchief.newlotterycheck.view.scan.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.const.LottoConst
import com.jeepchief.newlotterycheck.databinding.LottoNumberBinding
import com.jeepchief.newlotterycheck.util.ColorChecker
import com.jeepchief.newlotterycheck.util.Log

class LottNumberAdapter(
    vararg drwNumbers: Int,
    private val refNumbers: List<Int>,
    private val scanResult: ((Int) -> Unit)?
) : RecyclerView.Adapter<LottNumberAdapter.LottNumberViewHolder>() {
    private val drwNumList: List<Int>
    private var drwCount = 0
    init {
        drwNumList = drwNumbers.toList()
    }
    class LottNumberViewHolder(private val binding: LottoNumberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drwNumber: Int, isLast: Boolean, refNumbers: List<Int>, drawResult: (Int) -> Unit) {
            binding.tvRefDrwNumber.apply {
                drwNumber.toString().also { num ->
                    text = num
                    refNumbers.forEach { refNum ->
                        if(refNum == num.toInt()) {
                            drawResult.invoke(1)
//                            setTextColor(ColorChecker.convertColor(num.toInt()))
                            setBackgroundResource(ColorChecker.convertBackground(num.toInt()))
                        }
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
        holder.bind(drwNumList[position], position == 6, refNumbers) { drwCount += it }
        if(position == itemCount -1)
            scanResult?.invoke(getDrwResult(drwCount).also { Log.e("drwResult is $it, drwCount is $drwCount") })
    }

    private fun getDrwResult(drwCount: Int) : Int = when(drwCount) {
        6 -> LottoConst.DRAW_RESULT_1ST
        5 -> LottoConst.DRAW_RESULT_3RD
        4 -> LottoConst.DRAW_RESULT_4TH
        3 -> LottoConst.DRAW_RESULT_5TH
        else -> -1
    }
    override fun getItemCount(): Int = drwNumList.size
}