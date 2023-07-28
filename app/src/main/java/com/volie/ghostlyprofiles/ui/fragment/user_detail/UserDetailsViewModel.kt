package com.volie.ghostlyprofiles.ui.fragment.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
}