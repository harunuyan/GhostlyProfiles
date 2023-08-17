package com.volie.ghostlyprofiles.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.ghostlyprofiles.data.model.Country
import com.volie.ghostlyprofiles.data.model.RandomUserResponse
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.data.repo.Repository
import com.volie.ghostlyprofiles.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<RandomUserResponse>>()
    val users: LiveData<Resource<RandomUserResponse>> = _users

    fun getRandomUsers(nat: String, gender: String) {
        _users.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRandomUsersFromRemote(nat = nat, gender = gender)
            _users.postValue(result)
        }
    }

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

    fun getCountries(): Array<Country> {
        return repository.getCountries()
    }
}