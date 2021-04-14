package com.example.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginState.value = LoginState.Loading
        delay(2000L)

        if (username == "Ryosuke" && password == "Hello world"){
            _loginState.value = LoginState.Success
        }else{
            _loginState.value = LoginState.Error
        }
    }

    sealed class LoginState{

        object Success: LoginState()
        object Error: LoginState()
        object Loading: LoginState()

        // State Flow needs initial value so, you have to implement something value for StateFlow!!
        object Empty: LoginState()
    }
}