package com.akash.newzz_compose.ui.starwars.characterdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.akash.newzz.data.response.starwars.Character
import com.akash.newzz_compose.ui.commoncomposable.HeightSpacer
import com.akash.newzz_compose.ui.commoncomposable.RemoteImage

@Composable
fun CharacterDetailsScreen(character: Character, navController: NavController) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            HeightSpacer(value = 60.dp)
            RemoteImage(url = character.image, modifier = Modifier.requiredSize(screenWidth))
            Text(text = "Name : ${character.name}")
            Text(text = "Birth Year : ${character.birthYear}")
            Text(text = "gender : ${character.gender}")
            Text(text = "Height : ${character.height}")
            Text(text = "Eye Color ${character.eyeColor}")
            Text(text = "Hair Color : ${character.hairColor}")
            Text(text = "Skin Color : ${character.skinColor}")
            Text(text = "Mass : ${character.mass}")
            Text(text = "Link : ${character.image}")
        }
    }
}