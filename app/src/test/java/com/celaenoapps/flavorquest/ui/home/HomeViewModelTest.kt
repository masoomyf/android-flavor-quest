package com.celaenoapps.flavorquest.ui.home

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.celaenoapps.flavorquest.data.fake.FakeDataSource
import com.celaenoapps.flavorquest.testutils.TestDispatcherRule
import com.celaenoapps.flavorquest.testutils.TestRecipeRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val recipeRepository = TestRecipeRepository()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            recipeRepository = recipeRepository,
            savedStateHandle = SavedStateHandle()
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        viewModel.uiState.test {
            awaitItem()
            cancel()
        }
    }

    @Test
    fun stateIsUpdatedWithRecipes() = runTest {
        viewModel.uiState.test {
            recipeRepository.setRecipes(listOf(FakeDataSource.sampleResource))
            awaitItem()
            assertEquals(
                HomeFeedUiState.Success(listOf(FakeDataSource.sampleResource)),
                awaitItem()
            )
            cancel()
        }
    }


}