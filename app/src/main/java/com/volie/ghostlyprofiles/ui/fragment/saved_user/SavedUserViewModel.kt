package com.volie.ghostlyprofiles.ui.fragment.saved_user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedUserViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _savedUsers = MutableLiveData<List<User>>()
    val savedUsers: MutableLiveData<List<User>> = _savedUsers

    fun getSavedUsers() {
        viewModelScope.launch {
            val result = repository.getSavedUsersFromDatabase()
            _savedUsers.postValue(result)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            repository.deleteAllUsers()
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}