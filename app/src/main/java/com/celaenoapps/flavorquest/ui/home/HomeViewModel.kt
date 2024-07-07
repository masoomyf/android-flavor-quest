package com.celaenoapps.flavorquest.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.celaenoapps.flavorquest.data.RecipeModel
import com.celaenoapps.flavorquest.data.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var searchText: String by savedStateHandle.saveable {
        mutableStateOf("")
    }

    private val recipeFlow: StateFlow<List<RecipeModel>> = recipeRepository.getRecipes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val uiState: StateFlow<HomeFeedUiState> = recipeFlow.map { recipes ->
        if (recipes.isEmpty()) {
            HomeFeedUiState.Loading
        } else {
            HomeFeedUiState.Success(recipes)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeFeedUiState.Loading
    )

}

/**
 * A sealed hierarchy describing the home screen state.
 */
sealed interface HomeFeedUiState {
    /**
     * The home screen is loading.
     */
    data object Loading : HomeFeedUiState

    /**
     * The home screen has loaded.
     */
    data class Success(val recipes: List<RecipeModel>) : HomeFeedUiState

    /**
     * The home screen failed to load.
     */
    data class Error(val throwable: Throwable) : HomeFeedUiState

}