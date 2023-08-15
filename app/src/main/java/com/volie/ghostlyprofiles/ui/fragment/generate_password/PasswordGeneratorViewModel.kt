package com.volie.ghostlyprofiles.ui.fragment.generate_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.data.model.RandomUserResponse
import com.volie.ghostlyprofiles.data.repo.Repository
import com.volie.ghostlyprofiles.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordGeneratorViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _password = MutableLiveData<Resource<RandomUserResponse>>()
    val password: LiveData<Resource<RandomUserResponse>> = _password

    fun generatePassword(
        isUppercase: Boolean,
        isLowerCase: Boolean,
        isNumeric: Boolean,
        isSpecial: Boolean,
        passwordLength: Int
    ) {
        viewModelScope.launch {
            val options = mutableListOf<String>()

            if (isUppercase) options.add("upper") else options.remove("upper")
            if (isLowerCase) options.add("lower") else options.remove("lower")

            if (isNumeric) {
                options.add("number")
                options.remove("CHARSET")
            } else {
                options.remove("number")
                options.add("CHARSET")
            }

            if (isSpecial) options.add("special") else options.remove("special")

            val result = options.joinToString(",") + ",$passwordLength"

            val response = repository.generatePassword(result)

            _password.postValue(response)
        }
    }

    fun savePassword(password: Login) {
        viewModelScope.launch {
            repository.insertPassword(password)
        }
    }
}