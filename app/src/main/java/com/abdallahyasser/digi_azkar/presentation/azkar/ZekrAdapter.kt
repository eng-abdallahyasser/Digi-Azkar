package com.abdallahyasser.digi_azkar.presentation.azkar

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.abdallahyasser.digi_azkar.R
import com.abdallahyasser.digi_azkar.domain.azkar.Zekr
import kotlin.collections.mutableListOf

class ZekrAdapter() : Adapter<ZekrViewHolder>() {

    private var zekrList = mutableListOf<Zekr>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZekrViewHolder {
        val view = inflate(parent.context, R.layout.zekr_card, null)
        return ZekrViewHolder(view)

    }

    override fun onBindViewHolder(holder: ZekrViewHolder, position: Int) {
        holder.bind(zekrList[position])
    }

    override fun getItemCount(): Int {
        return zekrList.size
    }
    fun setData(newList: List<Zekr>) {
        zekrList.clear()
        zekrList.addAll(newList)
        notifyDataSetChanged()
    }
}

class ZekrViewHolder(view: View) : ViewHolder(view) {
    val view: View = view
    fun bind(zekr: Zekr) {
        view.findViewById<TextView>(R.id.zekrTitle).text = zekr.zekrTitle
        view.findViewById<TextView>(R.id.zekrTimes).text = zekr.repeat.toString()
        view.findViewById<TextView>(R.id.zekrText).text = zekr.zekrText
        view.findViewById<TextView>(R.id.zekrReference).text = zekr.reference

    }

}