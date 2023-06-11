package com.jeepchief.newlotterycheck.view.raffle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.LayoutRaffleDialogBinding
import com.jeepchief.newlotterycheck.databinding.SliderRaffleBinding
import com.jeepchief.newlotterycheck.util.Log
import com.jeepchief.newlotterycheck.view.BaseFragment
import com.jeepchief.newlotterycheck.view.raffle.adapter.RaffleNumAdapter
import kotlin.random.Random

class RaffleFragment : BaseFragment() {
    private var _binding: SliderRaffleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SliderRaffleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            flRaffleSlider.setOnClickListener {
                Log.e("slider click!!")

//                (mContext as MainActivity).hideActionBar()

                // todo: 추첨결과 캡쳐 후 공유기능 추가
                val dlgBinding = LayoutRaffleDialogBinding.inflate(layoutInflater)
                val dlg = AlertDialog.Builder(mContext).create().apply {
                    setCancelable(false)
                    setView(dlgBinding.root)
                }

                dlgBinding.apply {
                    rvRaffle.apply {
                        layoutManager = LinearLayoutManager(mContext)
                        adapter = RaffleNumAdapter()
                    }
                    btnStartRaffle.setOnClickListener {
                        val btn = (it as Button)
                        when(btn.text.toString()) {
                            getString(R.string.label_start_raffle) -> {
                                btn.text = getString(R.string.label_stop_raffle)
                                (rvRaffle.adapter as RaffleNumAdapter).startRaffle()
                            }
                            getString(R.string.label_stop_raffle) -> {
                                btn.text = getString(R.string.label_start_raffle)
                                (rvRaffle.adapter as RaffleNumAdapter).stopRaffle()
                            }
                        }
                    }
                    btnDialogClose.setOnClickListener { dlg.dismiss() }
                }
                dlg.show()
            }
        }
    }

    private fun createRaffleNums() : List<Int> {
        val list = mutableListOf<Int>()
        for(i in 0 until 6) {
            list.add(Random.nextInt(45))
        }
        return list
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}