package com.abdallahyasser.digi_azkar.domain

class GetAzkarUseCase(repo: AzkarRepositoryInter) {
    private val repository = repo

    suspend operator fun invoke(): List<Zekr> {
        return repository.getAzkar()
    }
}