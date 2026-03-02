package com.abdallahyasser.digi_azkar.presentation.azkar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.abdallahyasser.digi_azkar.R
import com.abdallahyasser.digi_azkar.domain.ZekrCategory

class ZekrCategoryAdapter(val zekrCategory:List<ZekrCategory> ): Adapter<ZekrCategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZekrCategoryViewHolder {
        val view = inflate(parent.context, R.layout.azkar_category_card, null)
        return ZekrCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZekrCategoryViewHolder, position: Int) {
        holder.bind(zekrCategory[position])
    }

    override fun getItemCount(): Int = zekrCategory.size

}

class ZekrCategoryViewHolder(view : View): ViewHolder(view) {


    fun bind(zekrCategory: ZekrCategory) {
        TODO("Not yet implemented")
    }

}