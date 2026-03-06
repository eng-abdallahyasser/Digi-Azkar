package com.abdallahyasser.digi_azkar.data.azkar.remote

import com.abdallahyasser.digi_azkar.domain.azkar.Zekr

fun AzkarResponse.toDomain() : List<Zekr> {

    val azkarList = mutableListOf<Zekr>()

    this.content.forEach { zekr ->
        azkarList.add(
            Zekr(
                zekrTitle = zekr.zekr,
                zekrText = zekr.zekr,
                repeat = zekr.repeat,
                bless = zekr.bless,
                reference = zekr.bless,
                category =zekr.bless,
            )
        )

    }

    return azkarList
}