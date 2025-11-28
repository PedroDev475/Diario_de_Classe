package com.senai.diario_de_classe.domain.model

data class Usuario (
    val id: String,
    val nome: String,
    val email: String,
    val cpf: String,
    val senha: String?,
    val fotoPerfilUrl: String?,
    val fotoLocalPath: String?,
    val anexos: List<String>?,
    val updatedAt: Long,
    val pendingSync: Boolean = false
)

