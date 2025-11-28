package com.senai.diario_de_classe.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.diario_de_classe.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel
    @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {

            val cached = repo.bootstrapSession()
            if (cached != null) {
                _state.value = HomeUiState(
                    nome = cached.nome,
                    email = cached.email,
                    loading = false
                )


                val refreshStillValid = repo.ensureFreshAccessLocallyOnly()
                if (refreshStillValid) return@launch
            }

            try {
                if (repo.ensureFreshAccess()) {
                    val me = repo.meAndCache()
                    _state.value = HomeUiState(
                        nome = me.nome,
                        email = me.email,
                        loading = false
                    )
                } else {
                    _state.value = HomeUiState(
                        error = "Sessão expirada. Faça login novamente.",
                        loading = false
                    )
                }
            } catch (e: Exception) {

                if (cached != null) {
                    _state.value = HomeUiState(
                        nome = cached.nome,
                        email = cached.email,
                        loading = false
                    )
                } else {
                    _state.value = HomeUiState(
                        error = "Sem conexão. Faça login novamente.",
                        loading = false
                    )
                }
            }
        }
    }

    fun logout() = viewModelScope.launch {
        repo.logout()
    }
}


