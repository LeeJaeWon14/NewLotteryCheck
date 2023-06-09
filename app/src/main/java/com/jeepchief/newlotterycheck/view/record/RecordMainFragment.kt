package com.jeepchief.newlotterycheck.view.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.FragmentRecordMainBinding
import com.jeepchief.newlotterycheck.view.BaseFragment
import com.jeepchief.newlotterycheck.view.main.MainActivity

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

        (mContext as MainActivity).hideActionBar()
        binding.apply {
            bnvRecord.apply {
                selectedItemId = R.id.menu_nav_statistic
                setOnItemSelectedListener { item ->
                    var nowFragment: Fragment? = null
                    childFragmentManager.fragments.forEach { fragment ->
                        if(fragment.isVisible) nowFragment = fragment
                    }

                    val transaction = childFragmentManager.beginTransaction()
                    when(item.itemId) {
                        R.id.menu_nav_statistic -> {
                            if(nowFragment is StatisticsFragment) return@setOnItemSelectedListener true
                            transaction
//                            .setCustomAnimations(0, 0)
                                .replace(R.id.fl_container_2, StatisticsFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.menu_nav_record -> {
                            if(nowFragment is RecordListFragment) return@setOnItemSelectedListener true
                            transaction
//                            .setCustomAnimations(0, 0)
                                .replace(R.id.fl_container_2, RecordListFragment())
                                .commitAllowingStateLoss()
                        }
                    }

                    true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (mContext as MainActivity).showActionBar()
        _binding = null
    }
}