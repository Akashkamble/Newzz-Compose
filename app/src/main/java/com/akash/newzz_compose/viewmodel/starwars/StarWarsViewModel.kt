package com.akash.newzz_compose.viewmodel.starwars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.newzz.data.Result
import com.akash.newzz.data.repository.starwars.StarWarsRepo
import com.akash.newzz.data.response.starwars.Character
import com.akash.newzz_compose.ui.starwars.starwarslist.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StarWarsViewModel @Inject constructor(
    val repo: StarWarsRepo
) : ViewModel() {

    private val _viewState = MutableStateFlow(CharacterListUiState())
    val viewState: StateFlow<CharacterListUiState> = _viewState

    init {
        viewModelScope.launch {
            val result = repo.getStarWarsCharacters()
            _viewState.value = getViewStateFromResult(result)
        }

    }

    private fun getViewStateFromResult(result: Result<List<Character>>): CharacterListUiState {
        return when (result) {
            is Result.Error -> {
                viewState.value.copy(
                    isLoading = false,
                    error = result.errorMessage
                )
            }
            is Result.Success -> {
                viewState.value.copy(
                    isLoading = false,
                    list = result.data
                )
            }
        }
    }

}