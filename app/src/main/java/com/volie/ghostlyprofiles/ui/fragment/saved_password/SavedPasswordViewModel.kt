package com.volie.ghostlyprofiles.ui.fragment.saved_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPasswordViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _savedPasswords = MutableLiveData<List<Login>>()
    val savedPassword: LiveData<List<Login>> = _savedPasswords

    fun getSavedPasswords() {
        viewModelScope.launch {
            val result = repository.getSavedPasswordsFromDatabase()
            _savedPasswords.postValue(result)
        }
    }

    fun deletePassword(password: Login) {
        viewModelScope.launch {
            repository.deletePassword(password)
        }
    }
}