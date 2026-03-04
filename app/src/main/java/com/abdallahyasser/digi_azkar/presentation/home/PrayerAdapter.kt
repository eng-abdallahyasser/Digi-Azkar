package com.abdallahyasser.digi_azkar.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.abdallahyasser.digi_azkar.databinding.ActivityMainBinding
import com.abdallahyasser.digi_azkar.databinding.PrayerCardBinding
import com.abdallahyasser.digi_azkar.domain.prayer.Prayer

class PrayerAdapter(val prayers: List<Prayer>) : Adapter<PrayerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PrayerViewHolder {
        val binding = PrayerCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrayerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PrayerViewHolder,
        position: Int
    ) {
        holder.bind(
            prayers[position]
        )
    }

    override fun getItemCount() = prayers.size
}

class PrayerViewHolder(val binding: PrayerCardBinding) : ViewHolder(binding.root) {


    fun bind(prayer: Prayer) {
        binding.prayerName.text = prayer.prayerName
        binding.prayerTime.text = prayer.prayerTime
    }


}