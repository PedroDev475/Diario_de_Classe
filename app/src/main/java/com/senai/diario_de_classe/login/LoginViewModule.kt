package com.senai.diario_de_classe.login

import android.R
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.ThreadLocalRandom.current

class LoginViewModule : ViewModel()  {
     private val _state = MutableStateFlow(LoginState())
     val state = _state.asStateFlow()

    fun onEmailChanged(email: String){
      _state.update  { currentState -->
              currentState.copy(email = email)

    }

}