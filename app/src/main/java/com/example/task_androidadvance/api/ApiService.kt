package com.example.task_androidadvance.api

import com.example.task_androidadvance.models.UserItem
import com.example.task_androidadvance.models.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): Response<Users>
}