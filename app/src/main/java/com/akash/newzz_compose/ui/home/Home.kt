package com.akash.newzz_compose.ui.home

import androidx.compose.Composable
import androidx.ui.material.Scaffold
import com.akash.newzz_compose.viewmodel.NewzzViewModel

/**
 * Created by Akash on 05/06/20
 */

@Composable
fun Home(
    viewModel: NewzzViewModel
) {
    Scaffold(
        bodyContent = { BodyContent(viewModel) },
        bottomBar = { BottomBar(viewModel) }
    )
}
