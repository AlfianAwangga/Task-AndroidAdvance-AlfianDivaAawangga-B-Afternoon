package com.example.task_androidadvance.repositories

import com.example.task_androidadvance.api.ApiService
import com.example.task_androidadvance.models.Users
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers(): Response<Users> = apiService.getUsers()
}