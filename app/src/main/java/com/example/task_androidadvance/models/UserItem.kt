package com.example.task_androidadvance.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite")
data class UserItem(
    val email: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
):Serializable