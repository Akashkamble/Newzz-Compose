package com.akash.newzz_compose.ui.starwars.starwarslist

import com.akash.newzz.data.Result
import com.akash.newzz.data.response.starwars.Character

data class CharacterListUiState(
    val isLoading: Boolean = true,
    val list: List<Character> = emptyList(),
    val error: String = ""
)