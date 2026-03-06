package com.abdallahyasser.digi_azkar.data.azkar

import com.abdallahyasser.digi_azkar.data.azkar.remote.AzkarApiService
import com.abdallahyasser.digi_azkar.data.azkar.remote.AzkarRetrofitClient
import com.abdallahyasser.digi_azkar.data.azkar.remote.toDomain
import com.abdallahyasser.digi_azkar.domain.azkar.AzkarRepositoryInter
import com.abdallahyasser.digi_azkar.domain.azkar.Zekr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AzkarRepoImpl : AzkarRepositoryInter {
    override suspend fun getAzkar(): List<Zekr>  {
        return try {
            val response = AzkarRetrofitClient.createService(AzkarApiService::class.java)

            val azkarList = mutableListOf<Zekr>()
            azkarList.addAll(response.getEveningAzkar().toDomain("evening"))
            azkarList.addAll(response.getMorningAzkar().toDomain("morning"))
            azkarList.addAll(response.getPostPrayerAzkar().toDomain("post prayer"))

            azkarList


        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}