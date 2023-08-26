package com.example.testapp.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.testapp.data.cache.models.CameraCache
import com.example.testapp.data.cache.models.DoorCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

@Database(
    entities = [CameraCache::class, DoorCache::class],
    version = 1,
    exportSchema = true
)

@TypeConverters(AppDatabase.DatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao

    class DatabaseConverter {
        @TypeConverter
        fun fromList(countryLang: List<String?>?): String? {
            if (countryLang == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return gson.toJson(countryLang, type)
        }

        @TypeConverter
        fun toList(countryLangString: String?): List<String>? {
            if (countryLangString == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            return gson.fromJson<List<String>>(countryLangString, type)
        }
    }
}