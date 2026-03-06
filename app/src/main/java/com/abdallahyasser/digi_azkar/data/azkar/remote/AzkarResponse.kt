package com.abdallahyasser.digi_azkar.data.azkar.remote

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class AzkarResponse(
    val title: String,
    val content:List<ZekrResponse>
)

@JsonClass(generateAdapter = true)
class ZekrResponse(
    val zekr: String,
    val bless: String,
    val repeat: Int)