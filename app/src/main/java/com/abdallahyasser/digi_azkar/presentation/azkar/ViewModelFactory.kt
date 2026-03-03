package com.abdallahyasser.digi_azkar.presentation.azkar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdallahyasser.digi_azkar.domain.azkar.GetAzkarUseCase

class ViewModelFactory(private val getAzkarUseCase: GetAzkarUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AzkarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AzkarViewModel(getAzkarUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}