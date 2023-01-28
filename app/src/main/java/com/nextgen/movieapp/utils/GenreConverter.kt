package com.nextgen.movieapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nextgen.movieapp.data.source.remote.response.GenresItem

object GenreConverter {

    @TypeConverter
    @JvmStatic
    fun toGenre(value: String?): GenresItem{
        val modelType =object : TypeToken<String?>(){}.type
        return Gson().fromJson(value, modelType)
    }

    @TypeConverter
    @JvmStatic
    fun fromGenre(value: GenresItem): String{
        return Gson().toJson(value)
    }

}