package com.jeepchief.newlotterycheck.view.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.SliderBinding
import com.jeepchief.newlotterycheck.util.Log
import com.jeepchief.newlotterycheck.view.BaseFragment
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class ScanFragment : BaseFragment() {
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
                setImageResource(R.drawable.noun_camera_3564682)
                setOnClickListener {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.slider_container, ScanResultFragment())
//                        .commitAllowingStateLoss()

                    qrInit()
                }
            }

            slideLabel.text = getString(R.string.label_scan)
        }
    }

    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
        it.contents?.let { content ->
            Log.e("QR content is $content")
//            processingData(content)
        } ?: run {
            Log.e("QR has no data.")
            Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun qrInit() {
        val qrOptions = ScanOptions().apply {
            setPrompt(getString(R.string.msg_qr_prompt))
            setBeepEnabled(false)
            captureActivity = EmptyActivity::class.java
        }
        barcodeLauncher.launch(qrOptions)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}