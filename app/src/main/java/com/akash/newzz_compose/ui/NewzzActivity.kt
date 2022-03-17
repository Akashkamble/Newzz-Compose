package com.akash.newzz_compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.livedata.observeAsState
import com.akash.newzz_compose.ui.newzzappui.NewzzAppUI
import com.akash.newzz_compose.ui.starwars.starwarslist.StarWarsListScreen
import com.akash.newzz_compose.ui.style.NewzzTheme
import com.akash.newzz_compose.viewmodel.NewzzViewModel
import com.akash.newzz_compose.viewmodel.starwars.StarWarsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Akash on 27/08/20
 */
@AndroidEntryPoint
class NewzzActivity : ComponentActivity() {

//    private val viewModel: NewzzViewModel by viewModels()
    private val viewModel: StarWarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewzzTheme(isDarkTheme = isSystemInDarkTheme()) {
                StarWarsListScreen(viewModel = viewModel)
            }
        }
    }
}
