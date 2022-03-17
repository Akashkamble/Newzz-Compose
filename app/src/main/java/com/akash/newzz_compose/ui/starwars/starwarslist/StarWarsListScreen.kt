package com.akash.newzz_compose.ui.starwars.starwarslist

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akash.newzz.data.response.starwars.Character
import com.akash.newzz_compose.ui.commoncomposable.RemoteImage
import com.akash.newzz_compose.ui.commoncomposable.WidthSpacer
import com.akash.newzz_compose.viewmodel.starwars.StarWarsViewModel

@Composable
fun  StarWarsListScreen(
    viewModel : StarWarsViewModel,
    modifier: Modifier = Modifier
) {
    val viewState = viewModel.viewState.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        CharacterListContent(viewState = viewState.value, modifier)
    }
}

@Composable
fun CharacterListContent(
    viewState: CharacterListUiState,
    modifier: Modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ){
        when{
            viewState.isLoading -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Loading..",
                        textAlign = TextAlign.Center
                    )
                }
            }
            viewState.list.isNotEmpty() -> {
                CharacterList(list = viewState.list)
            }
        }

    }
}

@Composable
fun CharacterList(list : List<Character>) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(list){ character ->
            CharacterRow(character = character) {
                Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CharacterRow(character : Character, onCharacterClick: ()->Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            onCharacterClick.invoke()
        }
    ) {
        RemoteImage(
            url = character.image,
            modifier = Modifier.requiredSize(60.dp)
        )
        WidthSpacer(value = 10.dp)
        Text(text = character.name)
    }
}