package com.abdallahyasser.digi_azkar.presentation.azkar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.abdallahyasser.digi_azkar.databinding.AzkarCategoryCardBinding
import com.abdallahyasser.digi_azkar.domain.azkar.ZekrCategory

class ZekrCategoryAdapter(): Adapter<ZekrCategoryViewHolder>() {


    private val zekrCategories = mutableListOf<ZekrCategory>()
    private val expandedCategories = mutableSetOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZekrCategoryViewHolder {
        val binding = AzkarCategoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ZekrCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZekrCategoryViewHolder, position: Int) {
        val category = zekrCategories[position]
        val isExpanded = expandedCategories.contains(position)

        // Setup the nested list adapter
        val zekrListAdapter = ZekrAdapter()
        zekrListAdapter.setData(category.zekrList)
        holder.binding.rvZekrList.adapter = zekrListAdapter

        // Set visibility and rotation based on expanded state
        holder.binding.rvZekrList.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.binding.ivChevron.rotation = if (isExpanded) 90f else 0f

        // Handle category card click
        holder.binding.zkerCardId.setOnClickListener {

            if (expandedCategories.contains(position)) {
                expandedCategories.remove(position)
            } else {
                expandedCategories.add(position)
            }
            notifyItemChanged(position)
        }
        holder.bind(category)
    }

    override fun getItemCount(): Int = zekrCategories.size

    fun setData(newList: List<ZekrCategory>) {
        zekrCategories.clear()
        zekrCategories.addAll(newList)
        expandedCategories.clear()
        notifyDataSetChanged()
    }

    fun updateShownCategories(shownCategories: List<Boolean>) {
        for (i in shownCategories.indices) {
            if (shownCategories[i]){
                expandedCategories.add(i)
            }
            else{
                expandedCategories.remove(i)
            }
        }
        notifyDataSetChanged()
    }
}

class ZekrCategoryViewHolder(val binding : AzkarCategoryCardBinding): ViewHolder(binding.root) {


    fun bind(zekrCategory: ZekrCategory) {
        binding.tvCategoryTitle.text = zekrCategory.categoryName
        binding.tvProgressText.text = "0/${zekrCategory.zekrList.size}"


    }

}