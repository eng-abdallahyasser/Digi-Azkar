package com.abdallahyasser.digi_azkar.data

import com.abdallahyasser.digi_azkar.data.remote.RetrofitClient
import com.abdallahyasser.digi_azkar.data.remote.api.PrayerTimesApi
import com.abdallahyasser.digi_azkar.data.remote.toDomain
import com.abdallahyasser.digi_azkar.domain.prayer.Prayer
import com.abdallahyasser.digi_azkar.domain.prayer.PrayerRepoInterface

class PrayerRepoImpl: PrayerRepoInterface {
    private val prayerTimesApi = RetrofitClient.createService(PrayerTimesApi::class.java)

    override suspend fun getPrayerTimes(city: String, country: String): List<Prayer> {
        return try {
            val response = prayerTimesApi.getPrayerTimesByCity(city, country)

            if (response.code == 200 && response.status == "OK") {
                response.toDomain()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}