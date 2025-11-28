package com.senai.diario_de_classe.data.mapper
import com.senai.diario_de_classe.data.entity.UsuarioEntity
import com.senai.diario_de_classe.data.UsuarioDto
import com.senai.diario_de_classe.domain.model.Usuario
fun  UsuarioDTO.toEntity (

    pending: Boolean = false,
    oldLocalPath: String? = null
) = UsuarioEntity(
    id = id,
    nome = nome,
    email = email,
    cpf = cpf,
    senha = null,
    fotoPerfilUrl = fotoPerfilUrl,
    fotoLocalPath = oldLocalPath,
    anexos = anexos,
    updatedAt = updatedAt,
    pendingSync = pending
)

fun UsuarioDto.toDomain() = Usuario(
    id = id,
    nome = nome,
    email = email,
    cpf = cpf,
    senha = senha,
    fotoPerfilUrl = fotoPerfilUrl,
    fotoLocalPath = fotoLocalPath,
    anexos = anexos ?: emptyList(),
    updatedAt = updatedAt
)



