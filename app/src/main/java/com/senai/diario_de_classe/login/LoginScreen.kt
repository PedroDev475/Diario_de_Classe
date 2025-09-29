package com.senai.diario_de_classe.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable

fun LoginScreen(loginViewModule: LoginViewModule = viewModel ()){
    val LoginUIState by loginViewModule.uiState.collectAsState()

}
