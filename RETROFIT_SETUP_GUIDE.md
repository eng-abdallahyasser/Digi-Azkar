# Retrofit Setup for Prayer Times API Integration

## Overview
This guide explains the complete Retrofit setup implemented in your project to fetch prayer times from the Aladhan API.

## Architecture Components

### 1. **Data Models** (`data/remote/models/`)
- **PrayerTimesResponse**: Root response object matching the API response
- **PrayerData, Timings, DateInfo**: Nested data classes for API response structure
- **HijriDate, GregorianDate, Weekday, Month**: Date-related models
- **Meta, Method, Location**: Metadata classes

All models use Moshi's `@JsonClass` annotation for automatic JSON serialization/deserialization.

### 2. **API Service** (`data/remote/api/PrayerTimesApi.kt`)
```kotlin
interface PrayerTimesApi {
    @GET("v1/timingsByCity")
    suspend fun getPrayerTimesByCity(
        @Query("city") city: String,
        @Query("country") country: String
    ): PrayerTimesResponse
}
```

### 3. **Retrofit Client** (`data/remote/RetrofitClient.kt`)
Singleton object that:
- Configures Moshi JSON adapter with Kotlin reflection support
- Creates and manages Retrofit instance
- Base URL: `https://api.aladhan.com/`

### 4. **Repository Implementation** (`data/PrayerRepoImpl.kt`)
Implements `PrayerRepoInterface` and:
- Uses PrayerTimesApi to fetch data from the API
- Transforms API response to domain Prayer objects
- Handles errors gracefully

## Usage Examples

### Basic Usage in Fragment
```kotlin
class PrayerFragment : Fragment() {
    private val viewModel: PrayerViewModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Fetch prayer times for Cairo, Egypt
        viewModel.getPrayerTimes("Cairo", "Egypt")
        
        // Observe the results
        viewModel.prayerTimes.observe(viewLifecycleOwner) { prayers ->
            // Update UI with prayers list
        }
    }
}
```

### API Response Mapping
The API returns:
```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "timings": {
      "Fajr": "04:52",
      "Sunrise": "06:19",
      "Dhuhr": "12:07",
      "Asr": "15:25",
      "Sunset": "17:55",
      "Maghrib": "17:55",
      "Isha": "19:12"
    },
    "date": { ... },
    "meta": { ... }
  }
}
```

Which is automatically parsed and converted to:
```kotlin
listOf(
    Prayer("Fajr", "04:52"),
    Prayer("Sunrise", "06:19"),
    Prayer("Dhuhr", "12:07"),
    Prayer("Asr", "15:25"),
    Prayer("Sunset", "17:55"),
    Prayer("Maghrib", "17:55"),
    Prayer("Isha", "19:12")
)
```

## Dependencies Added

### build.gradle.kts
```kotlin
implementation(libs.retrofit)
implementation(libs.retrofit.moshi)
implementation(libs.moshi.kotlin)
implementation(libs.okhttp)
```

### libs.versions.toml
```toml
retrofit = "2.11.0"
moshi = "1.15.1"
okhttp = "4.12.0"
```

## Features

✅ **Full API Integration**: Retrofit with Moshi JSON serialization
✅ **Type-Safe**: Strongly typed data models
✅ **Error Handling**: Try-catch blocks with graceful error handling
✅ **Coroutines**: Suspend functions for async operations
✅ **MVVM Pattern**: ViewModel with LiveData for UI state management
✅ **Repository Pattern**: Clean separation of concerns

## Advanced Usage

### Fetch Prayer Times for Different Cities
```kotlin
// Paris, France
viewModel.getPrayerTimes("Paris", "France")

// Tokyo, Japan
viewModel.getPrayerTimes("Tokyo", "Japan")

// Default: Cairo, Egypt
viewModel.getPrayerTimes()
```

### Custom Retrofit Configuration
To add interceptors (e.g., logging, authentication):

```kotlin
object RetrofitClient {
    private const val BASE_URL = "https://api.aladhan.com/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
```

## Project Structure
```
app/
├── src/main/java/com/abdallahyasser/digi_azkar/
│   ├── data/
│   │   ├── remote/
│   │   │   ├── api/
│   │   │   │   └── PrayerTimesApi.kt
│   │   │   ├── models/
│   │   │   │   └── PrayerTimesResponse.kt
│   │   │   └── RetrofitClient.kt
│   │   └── PrayerRepoImpl.kt
│   ├── domain/
│   │   ├── prayer/
│   │   │   ├── Prayer.kt
│   │   │   └── PrayerRepoInterface.kt
│   └── presentation/
│       └── prayers/
│           ├── PrayerViewModel.kt
│           ├── PrayerTimesFragment.kt
│           └── PrayerTimesAdapter.kt
```

## Troubleshooting

### Issue: API Not Returning Data
- Check internet connectivity
- Verify city and country names are correct
- Check API endpoint at https://api.aladhan.com/v1/timingsByCity?city=Cairo&country=Egypt

### Issue: JSON Parsing Error
- Ensure all API response fields are included in data models
- Check field names match the @Json annotations
- Verify Moshi adapter is correctly configured

### Issue: Network Timeout
- Increase timeout duration in RetrofitClient
- Check if API is accessible
- Verify network connectivity

