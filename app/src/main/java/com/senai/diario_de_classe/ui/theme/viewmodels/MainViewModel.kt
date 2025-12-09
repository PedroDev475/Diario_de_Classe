package com.senai.diario_de_classe.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.diario_de_classe.data.model.User
import com.senai.diario_de_classe.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject  constructor(
    private val repo: AuthRepository
) : ViewModel() {

    // Estado global: null = não logado, User = logado
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    // Variável para saber se ainda está carregando a verificação inicial
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        verificarSessao()
    }

    fun verificarSessao() {
        viewModelScope.launch {
            _isLoading.value = true
            val user = repo.bootstrapSession() //
            _currentUser.value = user
            _isLoading.value = false
        }
    }
}
