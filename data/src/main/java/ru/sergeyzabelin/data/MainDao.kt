package ru.sergeyzabelin.data

import androidx.room.*

@Dao
interface MainDao {
    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): MutableList<Character>
}