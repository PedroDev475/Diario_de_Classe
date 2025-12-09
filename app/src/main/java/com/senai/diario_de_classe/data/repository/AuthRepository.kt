package com.senai.diario_de_classe.data.repository

import com.senai.diario_de_classe.data.model.User

interface AuthRepository {
    suspend fun login(email: String, senha: String): User?
    suspend fun bootstrapSession(): User?
    suspend fun logout()

    suspend fun ensureFreshAccessLocallyOnly(): Boolean
    suspend fun ensureFreshAccess(): Boolean
    suspend fun meAndCache(): User
}
