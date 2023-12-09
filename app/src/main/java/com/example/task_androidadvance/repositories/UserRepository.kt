package com.example.task_androidadvance.repositories

import com.example.task_androidadvance.api.ApiClient
import com.example.task_androidadvance.database.FavoriteDatabase
import com.example.task_androidadvance.models.UserItem
import com.example.task_androidadvance.models.Users
import retrofit2.Response

class UserRepository(private val db: FavoriteDatabase) {
    suspend fun getUsers(): Response<Users> = ApiClient.apiService.getUsers()

    suspend fun getAllFavorites() = db.getFavoriteDao().getAllFavorites()

    suspend fun insertToFavorite(userItem: UserItem){
        db.getFavoriteDao().insertToFavorite(userItem)
    }

    suspend fun deleteFromFavorite(id: Int){
        db.getFavoriteDao().deleteFromFavorite(id)
    }
}