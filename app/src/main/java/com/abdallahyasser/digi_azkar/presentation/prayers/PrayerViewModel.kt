package com.abdallahyasser.digi_azkar.presentation.prayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallahyasser.digi_azkar.data.PrayerRepoImpl
import com.abdallahyasser.digi_azkar.domain.prayer.Prayer
import kotlinx.coroutines.launch

class PrayerViewModel : ViewModel() {
    private val prayerRepository = PrayerRepoImpl()

    private val _prayerTimes = MutableLiveData<List<Prayer>>()
    val prayerTimes: LiveData<List<Prayer>> = _prayerTimes

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getPrayerTimes(city: String = "Cairo", country: String = "Egypt") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val prayers = prayerRepository.getPrayerTimes(city, country)
                _prayerTimes.value = prayers
                _errorMessage.value = ""
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching prayer times: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

