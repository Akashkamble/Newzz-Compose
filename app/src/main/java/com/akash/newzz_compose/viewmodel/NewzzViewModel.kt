package com.akash.newzz_compose.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.newzz.data.Result
import com.akash.newzz.data.repository.NewsRepository
import com.akash.newzz_compose.other.Business
import com.akash.newzz_compose.other.Category
import com.akash.newzz_compose.other.General
import com.akash.newzz_compose.other.Technology
import kotlinx.coroutines.launch

/**
 * Created by Akash on 28/08/20
 */
class NewzzViewModel @ViewModelInject constructor(private val repo: NewsRepository) : ViewModel() {

    private val _isDarkTheme = MutableLiveData<Boolean>().apply { value = false }
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    private val _categoryList = MutableLiveData<List<Category>>().apply {
        value = listOf(General(), Business(), Technology())
    }
    val categoryList: LiveData<List<Category>> = _categoryList

    private val _activeCategory = MutableLiveData<Category>().apply {
        value = categoryList.value!![0]
    }
    val activeCategory: LiveData<Category> = _activeCategory

    fun performAction(action: Action) {
        when (action) {
            is Action.ChangePageTo -> {
                _activeCategory.value = action.category
            }
            is Action.FetchArticles -> {

            }
            Action.SwitchTheme -> {
                _isDarkTheme.value = !_isDarkTheme.value!!
                Log.d("TAG", "toggle: ${_isDarkTheme.value}")
            }
        }
    }


    fun getArticlesByCategory(
        category: String,
        page: Int = 1,
    ) {
        viewModelScope.launch {
            val result = repo.getArticlesByCategoryAsync(category, page)
            when (result) {
                is Result.Success -> {
                    Log.d("TAG", "list: ${result.data.articles}")
                }
                is Result.Error -> {
                    Log.d("TAG", "error : ${result.errorMessage}")

                }
            }
        }
    }


    sealed class Action {
        data class ChangePageTo(val category: Category) : Action()
        data class FetchArticles(val category: String) : Action()
        object SwitchTheme : Action()
    }
}