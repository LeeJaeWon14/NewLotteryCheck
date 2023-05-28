package com.jeepchief.newlotterycheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeepchief.newlotterycheck.model.rest.LotteryDTO
import com.jeepchief.newlotterycheck.model.rest.LotteryService
import com.jeepchief.newlotterycheck.model.rest.RetrofitClient
import kotlinx.coroutines.launch

class LotteryViewModel : ViewModel() {
    private val serviceClient = RetrofitClient.getInstance().create(LotteryService::class.java)

    val refDrwNumbers: MutableList<Int> = mutableListOf()
    private val _lottoNumbers: MutableLiveData<LotteryDTO> by lazy { MutableLiveData<LotteryDTO>() }
    val lottoNumbers: LiveData<LotteryDTO> get() = _lottoNumbers
    fun getLottoNumber(drwNo: String) {
        viewModelScope.launch {
            _lottoNumbers.value = serviceClient.getLotteryInfo(drwNo = drwNo)
            refDrwNumbers.run {
                add(lottoNumbers.value?.drwtNo1!!)
                add(lottoNumbers.value?.drwtNo2!!)
                add(lottoNumbers.value?.drwtNo3!!)
                add(lottoNumbers.value?.drwtNo4!!)
                add(lottoNumbers.value?.drwtNo5!!)
                add(lottoNumbers.value?.drwtNo6!!)
            }
        }
    }

//    private val _userLottNumbers = MutableLiveData<>
}