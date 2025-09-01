package com.senai.diario_de_classe

import Data.Aluno
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.HorizontalOrVertical
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.senai.diario_de_classe.ui.theme.Diario_de_ClasseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Diario_de_ClasseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        DiariodeClasseAPP(
                            modifier = Modifier.padding(innerPadding)

                        )


                }
            }
        }
    }
}




@Composable
fun DiariodeClasseAPP(modifier: Modifier = Modifier) {
    Card (
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Center,
            horizontalArrangement = HorizontalOrVertical

        ) {
            @Composable
            fun ListaDeAlunos(
                modifier: Modifier = Modifier,
                listaDeAlunos: List<Aluno>
            ) {
                LazyColumn(modifier = modifier) {
                    items(listaDeAlunos) { aluno ->
                        CardAluno(
                            modifier = modifier,
                            fotoAluno = R.drawable.ic_launcher_foreground,

                        )

                    }
                }
            }
        }
    }



















