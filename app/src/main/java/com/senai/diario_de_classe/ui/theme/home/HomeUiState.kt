package com.senai.diario_de_classe.ui.theme.home

data class HomeUiState(
    val nome: String = "",
    val email: String = "",
    val loading: Boolean = true,
    val error: String? = null
)