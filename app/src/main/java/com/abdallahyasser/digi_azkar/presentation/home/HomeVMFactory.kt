package com.abdallahyasser.digi_azkar.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdallahyasser.digi_azkar.domain.prayer.GetPrayerTimesUseCase
import com.abdallahyasser.digi_azkar.presentation.azkar.AzkarViewModel

class HomeVMFactory(private val getPrayerTimesUseCase: GetPrayerTimesUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(getPrayerTimesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}