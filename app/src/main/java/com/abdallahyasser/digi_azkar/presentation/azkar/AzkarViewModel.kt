package com.abdallahyasser.digi_azkar.presentation.azkar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallahyasser.digi_azkar.domain.azkar.GetAzkarUseCase
import com.abdallahyasser.digi_azkar.domain.azkar.ZekrCategory
import kotlinx.coroutines.launch

class AzkarViewModel(private val getAzkarUseCase: GetAzkarUseCase): ViewModel() {


    private val _azkarCategoriesShown = MutableLiveData<List<Boolean>>()

    private val _azkarCategories = MutableLiveData<List<ZekrCategory>>()
    val azkarCategories: LiveData<List<ZekrCategory>> = _azkarCategories
    val azkarCategoriesShown: LiveData<List<Boolean>> = _azkarCategoriesShown


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
            _azkarCategoriesShown.postValue(List(categories.size) { false })
        }
    }

    fun onCategoryClicked(zekrCategory: ZekrCategory) {

        val currentShownCategories = _azkarCategoriesShown.value ?: emptyList()

        val updatedShownCategories = currentShownCategories.toMutableList()

        val index = _azkarCategories.value?.indexOf(zekrCategory) ?: -1


        if (index != -1) {
            updatedShownCategories[index] = !updatedShownCategories[index]

        }

        _azkarCategoriesShown.postValue(updatedShownCategories)
    }
}
