package com.abdallahyasser.digi_azkar.domain.prayer

class GetPrayerTimesUseCase(val repo: PrayerRepoInterface) {

    suspend operator fun invoke(): List<Prayer> {
        return repo.getPrayerTimes()
    }

}
