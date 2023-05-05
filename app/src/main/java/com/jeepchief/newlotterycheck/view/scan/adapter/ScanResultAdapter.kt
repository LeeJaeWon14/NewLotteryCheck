package com.jeepchief.newlotterycheck.view.scan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.ItemScanResultBinding
import com.jeepchief.newlotterycheck.util.Log

class ScanResultAdapter(vararg drwNumbers: Int) : RecyclerView.Adapter<ScanResultAdapter.ScanResultViewHolder>() {
    private val drwNumList: List<Int>
    init {
        drwNumList = drwNumbers.toList()
    }
    class ScanResultViewHolder(private val binding: ItemScanResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drwNumber: Int, isLast: Boolean) {
            binding.tvRefDrwNumber.apply {
                text = drwNumber.toString()
                if(isLast)
                    setBackgroundColor(R.color.logo_color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanResultViewHolder {
        val binding = ItemScanResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScanResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScanResultViewHolder, position: Int) {
//        Log.e("number >> ${drwNumList[position]}")
        holder.bind(drwNumList[position], position == drwNumList.lastIndex)
    }

    override fun getItemCount(): Int = drwNumList.size
}