package com.abdallahyasser.digi_azkar.data

import android.content.Context
import com.abdallahyasser.digi_azkar.domain.azkar.AzkarRepositoryInter
import com.abdallahyasser.digi_azkar.domain.azkar.Zekr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.InputStreamReader

class AzkarRepoImpl(private val context: Context) : AzkarRepositoryInter {
    override suspend fun getAzkar(): List<Zekr> = withContext(Dispatchers.IO) {
        val azkarList = mutableListOf<Zekr>()
        try {
            context.assets.open("azkar.json").use { inputStream ->
                val reader = InputStreamReader(inputStream)
                val jsonString = reader.readText()
                val jsonArray = JSONArray(jsonString)

                for (i in 0 until jsonArray.length()) {

                    val jsonObject = jsonArray.getJSONObject(i)
                    val countValue = jsonObject.opt("count")
                    val count = when (countValue) {
                        is Int -> countValue
                        is String -> countValue.toIntOrNull() ?: 0
                        else -> 0
                    }

                    azkarList.add(
                        Zekr(
                            zekrTitle = jsonObject.optString("search"),
                            zekrText = jsonObject.optString("zekr"),
                            repeat = count,
                            bless = jsonObject.optString("description"),
                            reference = jsonObject.optString("reference"),
                            category = jsonObject.optString("category")
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        azkarList
    }
}
