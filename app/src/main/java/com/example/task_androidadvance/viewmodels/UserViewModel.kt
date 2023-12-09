package com.example.task_androidadvance.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_androidadvance.models.UserItem
import com.example.task_androidadvance.models.Users
import com.example.task_androidadvance.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<Response<Users>>()
    private val _favorites = MutableLiveData<List<UserItem>>()

    val users: LiveData<Response<Users>>
        get() = _users

    val favorites: LiveData<List<UserItem>>
        get() = _favorites

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            _users.value = response
        }
    }

    fun getAllFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.getAllFavorites()
            _favorites.postValue(list)
        }

    }

    fun insertToFavorite(userItem: UserItem){
        viewModelScope.launch {
            repository.insertToFavorite(userItem)
        }
    }

    fun deleteFromFavorite(id: Int){
        viewModelScope.launch{
            repository.deleteFromFavorite(id)
        }
    }
}