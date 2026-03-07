package com.abdallahyasser.digi_azkar.presentation.azkar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallahyasser.digi_azkar.domain.azkar.GetAzkarUseCase
import com.abdallahyasser.digi_azkar.domain.azkar.ZekrCategory
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class AzkarViewModel(private val getAzkarUseCase: GetAzkarUseCase): ViewModel() {



    private val _azkarCategories = MutableLiveData<List<ZekrCategory>>()
    val azkarCategories: LiveData<List<ZekrCategory>> = _azkarCategories


    init {
        loadAzkar()
    }

    private fun loadAzkar() {
        viewModelScope.launch {
            val allAzkar = getAzkarUseCase()
            val categories = allAzkar.groupBy { it.category }.map { (categoryName, zekrList) ->
                ZekrCategory(
                    categoryName = categoryName,
                    zekrList = zekrList,
                    progress = 0 // Initial progress
                )
            }
            _azkarCategories.postValue(categories)
        }


    }
}
