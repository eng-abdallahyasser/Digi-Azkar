package com.abdallahyasser.digi_azkar.presentation.azkar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.abdallahyasser.digi_azkar.databinding.AzkarCategoryCardBinding
import com.abdallahyasser.digi_azkar.domain.ZekrCategory

class ZekrCategoryAdapter(): Adapter<ZekrCategoryViewHolder>() {


    private val zekrCategories = mutableListOf<ZekrCategory>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZekrCategoryViewHolder {
        val binding = AzkarCategoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ZekrCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZekrCategoryViewHolder, position: Int) {
        holder.bind(zekrCategories[position])
    }

    override fun getItemCount(): Int = zekrCategories.size

    fun setData(newList: List<ZekrCategory>) {
        zekrCategories.clear()
        zekrCategories.addAll(newList)
        notifyDataSetChanged()
    }

}

class ZekrCategoryViewHolder(val binding : AzkarCategoryCardBinding): ViewHolder(binding.root) {


    fun bind(zekrCategory: ZekrCategory) {
        binding.tvCategoryTitle.text = zekrCategory.categoryName
        binding.tvProgressText.text = "0/${zekrCategory.zekrList.size}"
    }

}