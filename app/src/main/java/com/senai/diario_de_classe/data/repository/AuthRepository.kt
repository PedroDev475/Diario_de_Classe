package com.senai.diario_de_classe.data.repository
import com.senai.diario_de_classe.data.local.TokenStore
import com.senai.diario_de_classe.data.remote.ApiService
import javax.inject.Inject
class AuthRepository  @Inject constructor(
    private  val api: ApiService,
     private val store: TokenStore
)