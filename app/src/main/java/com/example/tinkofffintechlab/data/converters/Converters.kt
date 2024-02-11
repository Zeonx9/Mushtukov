package com.example.tinkofffintechlab.data.converters

import androidx.room.TypeConverter
import com.example.tinkofffintechlab.domain.model.Country
import com.example.tinkofffintechlab.domain.model.Genre

class Converters {
    @TypeConverter
    fun fromStringToGenreList(value: String?): List<Genre>? {
        return value?.split("<#>")?.map { Genre(it) }
    }

    @TypeConverter
    fun toStringFromGenreList(value: List<Genre>?): String? {
        return value?.joinToString(separator = "<#>") { it.genre }
    }

    @TypeConverter
    fun fromStringToCountryList(value: String?): List<Country>? {
        return value?.split("<#>")?.map { Country(it) }
    }

    @TypeConverter
    fun toStringFromCountryList(value: List<Country>?): String? {
        return value?.joinToString(separator = "<#>") { it.country }
    }
}