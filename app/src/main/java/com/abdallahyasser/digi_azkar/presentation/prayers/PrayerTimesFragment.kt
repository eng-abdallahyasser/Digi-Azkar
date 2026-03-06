package com.abdallahyasser.digi_azkar.presentation.prayers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import com.abdallahyasser.digi_azkar.data.prayer.PrayerRepoImpl


class PrayerTimesFragment : Fragment() {

    private val prayerRepository = PrayerRepoImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch prayer times for Cairo, Egypt (you can change this to any city/country)
        fetchPrayerTimes("Cairo", "Egypt")
    }



    private fun fetchPrayerTimes(city: String, country: String) {
        // Example of how to use the repository to fetch prayer times
        // In a real implementation, you would use a ViewModel with coroutines
        try {
            Log.d("PrayerFragment", "Fetching prayer times for $city, $country")
            // The actual network call would happen here through the ViewModel
            // For now, this shows the structure of how to use PrayerRepoImpl
        } catch (e: Exception) {
            Log.e("PrayerFragment", "Error fetching prayer times: ${e.message}")
        }
    }
}


