package com.nextgen.movieapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nextgen.movieapp.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllNews(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id)")
    suspend fun isFavoriteMovie(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

}