package com.jeepchief.newlotterycheck.view.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.const.LottoConst
import com.jeepchief.newlotterycheck.databinding.LayoutScanResultDialogBinding
import com.jeepchief.newlotterycheck.databinding.SliderScanBinding
import com.jeepchief.newlotterycheck.util.CrawlingManager
import com.jeepchief.newlotterycheck.util.Log
import com.jeepchief.newlotterycheck.view.BaseFragment
import com.jeepchief.newlotterycheck.view.scan.adapter.LottNumberAdapter
import com.jeepchief.newlotterycheck.view.scan.adapter.UserLottNumAdapter
import com.jeepchief.newlotterycheck.viewmodel.LotteryViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScanFragment : BaseFragment() {
    private var _binding: SliderScanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LotteryViewModel by activityViewModels()
    private var scanUrlParam = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SliderScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        binding.apply {
            flScanSlider.setOnClickListener {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.slider_container, ScanResultFragment())
//                        .commitAllowingStateLoss()

                qrInit()
            }
        }
    }

    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
        it.contents?.let { content ->
            Log.e("QR content is $content")
//            processingData(content)

            // 로또 QR만 허용
            if(!content.contains("lott")) {
                Toast.makeText(mContext, getString(R.string.msg_not_allowed_qr), Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }

            // Lotto URL pattern >> baseURL?v={해당회차}q091028353644...
            val targetDrw = content.split("v=")[1].substring(0, 4)
            scanUrlParam = content.split("v")[1]
            viewModel.getLottoNumber(targetDrw)
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

    private fun observeViewModel() {
        viewModel.lottoNumbers.observe(requireActivity()) { lottery ->
            Log.e("lottery response >> $lottery")
            CoroutineScope(Dispatchers.Main).launch {
                var userLottList: List<List<Int>>? = null
                withContext(Dispatchers.IO) {
                    userLottList = CrawlingManager.scanNumbers(scanUrlParam)
                }

                val dlgView = LayoutScanResultDialogBinding.inflate(layoutInflater)
                val dlg = AlertDialog.Builder(mContext).create().apply {
                    setCancelable(false)
                    setView(dlgView.root)
                    window?.setBackgroundDrawableResource(R.drawable.dialog_border)
                }

                dlgView.apply {
                    tvDrwCount.text = String.format(
                        getString(R.string.label_drw_count),
                        lottery.drwNo.toString()
                    )

                    rvRefLottNumbers.apply {
                        val manager = LinearLayoutManager(mContext).apply {
                            orientation = LinearLayoutManager.HORIZONTAL
                        }
                        layoutManager = manager
                        adapter = LottNumberAdapter(
                            lottery.drwtNo1, lottery.drwtNo2, lottery.drwtNo3,
                            lottery.drwtNo4, lottery.drwtNo5, lottery.drwtNo6, lottery.bnusNo,
                            refNumbers = viewModel.refDrwNumbers,
                            scanResult = null
                        )
//                    addItemDecoration(
//                        DividerItemDecoration(mContext, manager.orientation)
//                    )
                    }

                    rvUserLottNumbers.apply {
                        layoutManager = LinearLayoutManager(mContext)
                        userLottList?.let {
                            adapter = UserLottNumAdapter(it, viewModel.refDrwNumbers) { drwResult ->
                                val result = when(drwResult) {
                                    LottoConst.DRAW_RESULT_1ST -> "1등에 당첨되셨습니다."
                                    LottoConst.DRAW_RESULT_2ND -> "2등에 당첨되셨습니다."
                                    LottoConst.DRAW_RESULT_3RD -> "3등에 당첨되셨습니다."
                                    LottoConst.DRAW_RESULT_4TH -> "4등에 당첨되셨습니다."
                                    LottoConst.DRAW_RESULT_5TH -> "5등에 당첨되셨습니다."
                                    else -> "아쉽게도 낙첨되셨습니다."
                                }
                                tvDrwResult.text = result
                            }
                        } ?: run {
                            Toast.makeText(mContext, "Error!", Toast.LENGTH_SHORT).show()
                            return@launch
                        }

                    }

                    btnDialogClose.setOnClickListener { dlg.dismiss() }
                }
                //Add parameters in LotteryDTO.kt  LeeJaeWon A minute ago
                dlg.show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}