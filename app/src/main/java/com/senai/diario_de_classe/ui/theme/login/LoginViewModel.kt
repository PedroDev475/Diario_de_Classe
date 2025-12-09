package com.senai.diario_de_classe.ui.theme.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.diario_de_classe.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    var login by mutableStateOf("")
        private set

    var senha by mutableStateOf("")
        private set

    fun mudarTextoLogin(textoLogin: String) {
        login = textoLogin
        reset()
    }

    fun mudarTextoSenha(textoSenha: String) {
        senha = textoSenha
        reset()
    }

    fun logar() {
        viewModelScope.launch {
            val usuario = repository.login(login, senha)

            if (usuario != null) {
                _uiState.update { currentState ->
                    currentState.copy(loginSucesso = true, errouLoginOuSenha = false)

                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(errouLoginOuSenha = true, loginSucesso = false)

                }
            }
        }
    }

    fun reset() {
        _uiState.update { currentState ->
            currentState.copy(
                errouLoginOuSenha = false,
                loginSucesso = false
            )
        }
    }
}