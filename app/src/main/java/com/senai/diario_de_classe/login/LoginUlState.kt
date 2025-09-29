package com.senai.diario_de_classe.login

import androidx.lifecycle.ViewModel

 data class LoginUlState(
 val LabelLogin : String = "",
 val LabelSenha : String = "",
 val IsErrorLogin : Boolean = false,
 val IsLogado : Boolean = false

)

