package com.nextgen.movieapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.nextgen.movieapp.data.source.local.entity.MovieEntity
import com.nextgen.movieapp.utils.GenreConverter

@Database(
    entities =[MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}