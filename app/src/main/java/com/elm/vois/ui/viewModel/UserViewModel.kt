package com.elm.vois.ui.viewModel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elm.vois.data.models.User
import com.elm.vois.data.models.UserSearch
import com.elm.vois.data.network.RetrofitInstance

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun searchUsers(query: String, page: Int = 1) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response: Response<UserSearch> = RetrofitInstance.api.searchUsers(query, page)
                if (response.isSuccessful) {
                    _users.value = response.body()?.items ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
