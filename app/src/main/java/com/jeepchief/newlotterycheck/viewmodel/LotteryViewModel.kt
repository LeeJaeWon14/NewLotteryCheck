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

    private val _lottoNumbers: MutableLiveData<LotteryDTO> by lazy { MutableLiveData<LotteryDTO>() }
    val lottoNumbers: LiveData<LotteryDTO> get() = _lottoNumbers
    fun getLottoNumber(drwNo: String) {
        viewModelScope.launch {
            _lottoNumbers.value = serviceClient.getLotteryInfo(drwNo = drwNo)
        }
    }
}