package com.abdallahyasser.digi_azkar.data.prayer.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrayerTimesResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val data: PrayerData
)

@JsonClass(generateAdapter = true)
data class PrayerData(
    @Json(name = "timings")
    val timings: Timings,
    @Json(name = "date")
    val date: DateInfo,
    @Json(name = "meta")
    val meta: Meta
)

@JsonClass(generateAdapter = true)
data class Timings(
    @Json(name = "Fajr")
    val fajr: String,
    @Json(name = "Sunrise")
    val sunrise: String,
    @Json(name = "Dhuhr")
    val dhuhr: String,
    @Json(name = "Asr")
    val asr: String,
    @Json(name = "Sunset")
    val sunset: String,
    @Json(name = "Maghrib")
    val maghrib: String,
    @Json(name = "Isha")
    val isha: String,
    @Json(name = "Imsak")
    val imsak: String,
    @Json(name = "Midnight")
    val midnight: String,
    @Json(name = "Firstthird")
    val firstthird: String,
    @Json(name = "Lastthird")
    val lastthird: String
)

@JsonClass(generateAdapter = true)
data class DateInfo(
    @Json(name = "readable")
    val readable: String,
    @Json(name = "timestamp")
    val timestamp: String,
    @Json(name = "hijri")
    val hijri: HijriDate,
    @Json(name = "gregorian")
    val gregorian: GregorianDate
)

@JsonClass(generateAdapter = true)
data class HijriDate(
    @Json(name = "date")
    val date: String,
    @Json(name = "format")
    val format: String,
    @Json(name = "day")
    val day: String,
    @Json(name = "weekday")
    val weekday: Weekday,
    @Json(name = "month")
    val month: Month,
    @Json(name = "year")
    val year: String
)

@JsonClass(generateAdapter = true)
data class GregorianDate(
    @Json(name = "date")
    val date: String,
    @Json(name = "format")
    val format: String,
    @Json(name = "day")
    val day: String,
    @Json(name = "weekday")
    val weekday: Weekday,
    @Json(name = "month")
    val month: Month,
    @Json(name = "year")
    val year: String
)

@JsonClass(generateAdapter = true)
data class Weekday(
    @Json(name = "en")
    val en: String,
    @Json(name = "ar")
    val ar: String? = null
)

@JsonClass(generateAdapter = true)
data class Month(
    @Json(name = "number")
    val number: Int,
    @Json(name = "en")
    val en: String,
    @Json(name = "ar")
    val ar: String? = null,
    @Json(name = "days")
    val days: Int? = null
)

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "longitude")
    val longitude: Double,
    @Json(name = "timezone")
    val timezone: String,
    @Json(name = "method")
    val method: Method,
    @Json(name = "latitudeAdjustmentMethod")
    val latitudeAdjustmentMethod: String,
    @Json(name = "midnightMode")
    val midnightMode: String,
    @Json(name = "school")
    val school: String
)

@JsonClass(generateAdapter = true)
data class Method(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "params")
    val params: Map<String, Double>,
    @Json(name = "location")
    val location: Location
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "longitude")
    val longitude: Double
)

