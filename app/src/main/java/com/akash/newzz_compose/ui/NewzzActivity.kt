package com.akash.newzz_compose.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import androidx.ui.material.MaterialTheme
import com.akash.newzz_compose.style.darkThemeColor
import com.akash.newzz_compose.style.themeColor
import com.akash.newzz_compose.ui.home.Home
import com.akash.newzz_compose.viewmodel.NewzzViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewzzActivity : AppCompatActivity() {
    private val viewModel: NewzzViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = viewModel.isDarkTheme.observeAsState(false)
            MaterialTheme(colors = if (isDark.value) darkThemeColor else themeColor) {
                Home(viewModel)
            }
        }
    }
}
