package com.abdallahyasser.digi_azkar.domain.prayer

interface PrayerRepoInterface{
    suspend fun getPrayerTimes(city: String = "Cairo", country: String = "Egypt"): List<Prayer>
}
