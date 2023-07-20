package com.volie.ghostlyprofiles.ui.fragment.filtered_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.ghostlyprofiles.data.model.RandomUserResponse
import com.volie.ghostlyprofiles.data.repo.Repository
import com.volie.ghostlyprofiles.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilteredUserViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<RandomUserResponse>>()
    val users: LiveData<Resource<RandomUserResponse>> = _users

    fun getAccordingToIncludeField(fields: String) {
        _users.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAccordingToIncludeField(fields = fields)
            _users.postValue(result)
        }
    }
}