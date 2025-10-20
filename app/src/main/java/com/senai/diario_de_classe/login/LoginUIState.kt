package com.senai.diario_de_classe.login



 data class LoginUlState(
 val errouLoginOuSenha : Boolean = false,
 val labelLogin : String = "Login",
 val labelSenha : String = "Senha",
 val loginSucesso : Boolean = false

)

