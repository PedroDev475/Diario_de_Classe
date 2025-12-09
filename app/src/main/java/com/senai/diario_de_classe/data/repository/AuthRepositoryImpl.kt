package com.senai.diario_de_classe.data.repository


import com.senai.diario_de_classe.data.model.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    // private val firebaseAuth: FirebaseAuth (Futuro)
) : AuthRepository {

    // --- FUNÇÃO 1: LOGIN ---
    override suspend fun login(email: String, senha: String): User? {
        // SIMULAÇÃO DE AUTENTICAÇÃO
        if (email == "aluno@senai.br" && senha == "1234") {
            return User(nome = "Aluno Exemplar", email = email)
        } else {
            return null
        }
    }
    // --- FUNÇÃO 2: BOOTSTRAP ---
    override suspend fun bootstrapSession(): User? {
        return null
    }

    // --- FUNÇÃO 3: LOGOUT ---
    override suspend fun logout() {
        // Lógica de sair
    }


    override suspend fun ensureFreshAccessLocallyOnly(): Boolean {
        return true
    }

    override suspend fun ensureFreshAccess(): Boolean {
        return true
    }

    override suspend fun meAndCache(): User {
        return User("Aluno Exemplar", "aluno@senai.br")
    }

}