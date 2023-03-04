package com.jeepchief.newlotterycheck.view.raffle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.SliderBinding

class RaffleFragment : Fragment() {
    private var _binding: SliderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SliderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            slideImage.apply {
                setImageResource(R.drawable.noun_lottery_1501474)
                setOnClickListener {

                }
            }
            slideLabel.text = getString(R.string.label_raffle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}