package com.senai.diario_de_classe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// Se tiver o arquivo de tema, mantenha o import. Se não, use MaterialTheme no código.
// import com.senai.diario_de_classe.ui.theme.DiarioDeClasseTheme

// --- 1. ADICIONEI AS CLASSES QUE FALTAVAM AQUI ---
data class Aluno(
    val nome: String,
    val curso: String,
    @DrawableRes val foto: Int
)

class DataSource {
    fun carregarAlunos(): List<Aluno> {
        return listOf(
            // Troque R.drawable.ic_launcher_foreground pelas suas fotos reais se tiver
            Aluno("João Silva", "DS", android.R.drawable.btn_star_big_on),
            Aluno("Maria Oliveira", "Redes", android.R.drawable.star_on),
            Aluno("Pedro Santos", "Mecatrônica", android.R.drawable.ic_menu_camera)
        )
    }
}
// --------------------------------------------------

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "MainActivity"
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate Called")

        enableEdgeToEdge()
        setContent {
            // Usando MaterialTheme direto para evitar erro se não tiver o arquivo de tema
            MaterialTheme {
                Scaffold(
                    topBar = {
                        DiarioDeClasseTopBar()
                    }
                ) { innerPadding ->
                    DiarioDeClasseApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun DiarioDeClasseApp(
    modifier: Modifier = Modifier
) {
    // Surface ajuda a definir o fundo padrão
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        ListaDeAlunos(
            listaDeAlunos = DataSource().carregarAlunos()
        )
    }
}

@Composable
fun ListaDeAlunos(
    modifier: Modifier = Modifier,
    listaDeAlunos: List<Aluno>
) {
    LazyColumn(modifier = modifier) {
        items(listaDeAlunos) { aluno ->
            CardAluno(
                fotoAluno = aluno.foto,
                nomeAluno = aluno.nome,
                cursoAluno = aluno.curso
            )
        }
    }
}

@Composable
fun CardAluno(
    modifier: Modifier = Modifier, // Modifier que vem de fora (margens do card)
    @DrawableRes fotoAluno: Int,
    nomeAluno: String,
    cursoAluno: String
) {
    var expandir by remember { mutableStateOf(false) }

    Card(
        modifier = modifier // Aplica o modifier recebido APENAS no Card
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(
            bottomEnd = 0.dp,
            topStart = 0.dp,
            bottomStart = 20.dp,
            topEnd = 20.dp
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        // CORREÇÃO: Usar Modifier (novo) para os filhos, e não 'modifier' (parametro)
        Row(
            modifier = Modifier // Modifier NOVO para a linha
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = fotoAluno),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier // Modifier NOVO para a imagem
                    .size(64.dp) // Tamanho fixo menor
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f) // Ocupa o espaço do meio
            ) {
                Text(
                    text = nomeAluno,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = cursoAluno,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            DetalhesAlunoButton(
                onClick = { expandir = !expandir },
                // Modifier NOVO para o botão
                modifier = Modifier.wrapContentSize()
            )
        }
        if (expandir) {
            DetalhesAluno()
        }
    }
}

@Composable
fun DetalhesAlunoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DetalhesAluno() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Nota: 100")
        Text(text = "Faltas: 20%")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiarioDeClasseTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text("Diario de Classe")
        },
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun DiarioDeClassePreview() {
    MaterialTheme {
        Scaffold(
            topBar = {
                DiarioDeClasseTopBar()
            }
        ) { innerPadding ->
            DiarioDeClasseApp(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
