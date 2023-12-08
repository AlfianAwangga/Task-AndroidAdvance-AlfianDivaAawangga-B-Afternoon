package com.example.task_androidadvance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_androidadvance.models.UserItem
import com.example.task_androidadvance.models.Users
import com.example.task_androidadvance.repositories.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<Response<Users>>()

    val users: LiveData<Response<Users>>
        get() = _users

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            _users.value = response
        }
    }
}