package com.example.tinkofffintechlab.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tinkofffintechlab.data.converters.Converters
import com.example.tinkofffintechlab.domain.dao.FilmDao
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails

@Database(entities = [Film::class, FilmDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
}