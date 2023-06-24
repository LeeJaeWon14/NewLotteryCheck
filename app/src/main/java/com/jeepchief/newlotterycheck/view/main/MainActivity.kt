package com.jeepchief.newlotterycheck.view.main

import android.Manifest
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.jeepchief.newlotterycheck.BaseActivity
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.ActivityMainBinding
import com.jeepchief.newlotterycheck.util.Log
import com.jeepchief.newlotterycheck.view.main.adapter.ViewPagerAdapter
import com.jeepchief.newlotterycheck.viewmodel.LotteryViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = viewModels<LotteryViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate()")
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()
        initUi()
    }

    private fun initUi() {
        Log.e("initUi()")
        binding.apply {
            supportActionBar?.apply {
                val titleString: Spannable = SpannableString(LocalDate.now().toString())
                titleString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    0,
                    titleString.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )

                val subTitleString = SpannableString("다음 추첨일 : ${getNextRaffleDay()}")
                subTitleString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    0,
                    subTitleString.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )

                title = titleString
                subtitle = subTitleString
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
            }

            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
//            viewPager.setPageTransformer(DepthPageTransformer())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

        }

        return false
    }

    private fun checkPermission() {
        Log.e("checkPermission()")
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                /* no-op */
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity, getString(R.string.msg_permission_denied), Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.create().apply {
            setPermissionListener(permissionListener)
            setDeniedMessage(getString(R.string.msg_permission_denied))
            setPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }.check()
    }

    private fun getNextRaffleDay() : String {
        val now = LocalDate.now()
        val dayOfWeek = now.dayOfWeek
        return when(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREA)) {
            "월" -> "5일 뒤"
            "화" -> "4일 뒤"
            "수" -> "3일 뒤"
            "목" -> "2일 뒤"
            "금" -> "내일"
            "토" -> "오늘"
            "일" -> "6일 뒤"
            else -> ""
        }
    }

    fun hideActionBar() = actionBar?.hide()
    fun showActionBar() = actionBar?.show()

    //Fragment 전환 애니메이션
    // todo : position 이슈로 해결 전까지 보류
    class DepthPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            val pageWidth = view.width
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.alpha = 1f
                view.translationX = 0f
                view.scaleX = 1f
                view.scaleY = 1f
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.alpha = 1 - position

                // Counteract the default slide transition
                view.translationX = pageWidth * -position

                // Scale the page down (between MIN_SCALE and 1)
                val scaleFactor = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
            }
        }

        companion object {
            private const val MIN_SCALE = 0.75f
        }
    }
}