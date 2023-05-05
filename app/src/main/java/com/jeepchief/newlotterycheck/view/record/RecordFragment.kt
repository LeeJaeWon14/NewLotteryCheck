package com.jeepchief.newlotterycheck.view.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeepchief.newlotterycheck.databinding.SliderRecordBinding
import com.jeepchief.newlotterycheck.util.Log

class RecordFragment : Fragment() {
    private var _binding: SliderRecordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SliderRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            flRecordSlider.setOnClickListener {
                Log.e("slider click!!")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}