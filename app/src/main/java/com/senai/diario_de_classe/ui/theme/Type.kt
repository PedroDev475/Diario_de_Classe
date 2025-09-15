package com.senai.diario_de_classe.ui.theme



import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



val AbrilFatface = FontFamily(
    Font(com.senai.diario_de_classe.R.font.abril_fatface_regular)
)

val Montserrat = FontFamily(
    Font(com.senai.diario_de_classe.R.font.montserrat_regular),
    Font(com.senai.diario_de_classe.R.font.montserrat_bold, FontWeight.Bold)

)
val AppTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),

)