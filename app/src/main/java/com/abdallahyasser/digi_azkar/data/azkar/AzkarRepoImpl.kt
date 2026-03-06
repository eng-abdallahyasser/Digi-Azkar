package com.abdallahyasser.digi_azkar.data.azkar

import android.content.Context
import com.abdallahyasser.digi_azkar.data.azkar.remote.AzkarRetrofitClient
import com.abdallahyasser.digi_azkar.data.azkar.remote.toDomain
import com.abdallahyasser.digi_azkar.domain.azkar.AzkarRepositoryInter
import com.abdallahyasser.digi_azkar.domain.azkar.Zekr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AzkarRepoImpl(private val context: Context) : AzkarRepositoryInter {

    override suspend fun getAzkar(): List<Zekr> = withContext(Dispatchers.IO) {
        try {
            val response = AzkarRetrofitClient.service.getMorningAzkar()
            response.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
