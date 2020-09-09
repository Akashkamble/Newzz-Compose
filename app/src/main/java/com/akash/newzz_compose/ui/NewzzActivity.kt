package com.akash.newzz_compose.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.akash.newzz_compose.ui.newzzappui.NewzzAppUI
import com.akash.newzz_compose.ui.style.NewzzTheme
import com.akash.newzz_compose.viewmodel.NewzzViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Akash on 27/08/20
 */
@AndroidEntryPoint
class NewzzActivity : AppCompatActivity() {

    private val viewModel: NewzzViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme = viewModel.isDarkTheme.observeAsState(false)
            NewzzTheme(darkTheme = darkTheme.value) {
                NewzzAppUI(viewModel = viewModel)
            }
        }
    }
}
