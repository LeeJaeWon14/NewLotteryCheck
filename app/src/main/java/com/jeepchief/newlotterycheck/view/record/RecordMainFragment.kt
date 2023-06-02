package com.jeepchief.newlotterycheck.view.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.FragmentRecordMainBinding
import com.jeepchief.newlotterycheck.view.BaseFragment

class RecordMainFragment : BaseFragment() {
    private var _binding: FragmentRecordMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            bnvRecord.setOnItemSelectedListener { item ->
                var nowFragment: Fragment? = null
                parentFragmentManager.fragments.forEach { fragment ->
                    if(fragment.isVisible) nowFragment = fragment
                }

                val transaction = parentFragmentManager.beginTransaction()
                when(item.itemId) {
                    R.id.menu_nav_statistic -> {

                    }
                    R.id.menu_nav_record -> {

                    }
                }

                true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}