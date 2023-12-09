package com.example.task_androidadvance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task_androidadvance.models.UserItem

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertToFavorite(userItem: UserItem)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun deleteFromFavorite(id: Int)

    @Query("SELECT*FROM favorite")
    fun getAllFavorites(): List<UserItem>
}