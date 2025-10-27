package com.senai.diario_de_classe.data

import androidx.annotation.DrawableRes

 data  class Aluno (
    val nome: String,
    @DrawableRes val foto: Int,
    val curso: String,

)