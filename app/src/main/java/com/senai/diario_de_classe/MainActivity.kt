
import Data.Aluno
import Data.DataSource
import android.R.attr.contentDescription
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senai.diario_de_classe.R
import com.senai.diario_de_classe.ui.theme.Diario_de_ClasseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "MainActivity"
        super.onCreate(savedInstanceState)
        Log.e(TAG,"onCreated Called")

        enableEdgeToEdge()
        setContent {
            Diario_de_ClasseTheme {
                Scaffold(
                    topBar = {
                    DiarioDeClasseTopBar()
                    }
                )    { innerPadding ->
                          DiarioDeClasseApp(
                              modifier = Modifier
                                  .padding(innerPadding)
                                  .fillMaxSize()
                                  .statusBarsPadding()
                          )
                }
            }
        }
    }
}


@Composable
fun DiarioDeClasseApp(
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        ListaDeAlunos(
            modifier = modifier,
            listaDeAlunos = DataSource().carregarAlunos()
        )
    }
}

@Composable
fun ListaDeAlunos(
    modifier: Modifier = Modifier,
    listaDeAlunos: List<Aluno>,
) {
    LazyColumn(modifier = modifier) {
        items(listaDeAlunos) { aluno ->
            CardAluno(
                modifier = modifier,
                nomeAluno = aluno.nome,
                cursoAluno = aluno.curso,
                fotoAluno = aluno.foto

            )
        }
    }
}

@Composable
fun CardAluno(
    modifier: Modifier = Modifier,
    @DrawableRes fotoAluno:  Int,
    nomeAluno: String,
    cursoAluno: String,
) {
    var expandir by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize(
                animationSpec = spring(
                     dampingRatio = Spring.DampingRatioNoBouncy,
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
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
              Image(
                  painter = painterResource(id = fotoAluno),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                      .size(100.dp)
                      . weight(1f)
                      .clip (CircleShape)
              )
            Column {
                Text(text = nomeAluno,
                    modifier.fillMaxWidth()
                )
                Text(
                    text = cursoAluno,
                    modifier.fillMaxWidth()
                )
            }
            DetalhesAlunoButton(
                { expandir = !expandir},
                modifier = modifier
                    .weight(0.5f)
                    .wrapContentSize(align = Alignment.CenterEnd)
            )
        }
        if (expandir){
            DetalhesAluno()
        }
    }
}