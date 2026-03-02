package com.abdallahyasser.digi_azkar.domain

interface AzkarRepositoryInter {
    suspend fun getAzkar(): List<Zekr>
}