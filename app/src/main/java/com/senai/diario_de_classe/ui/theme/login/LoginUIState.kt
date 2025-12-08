package com.senai.diario_de_classe.ui.theme.login

data class LoginUIState (
    val errouLoginOuSenha: Boolean = false,
    val labelLogin:String = "Login",
    val labelSenha:String = "Senha",
    val loginSucesso: Boolean = false
)