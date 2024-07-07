package com.celaenoapps.flavorquest.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.celaenoapps.flavorquest.R
import com.celaenoapps.flavorquest.data.RecipeModel
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun circularProgressIndicator_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            HomeScreen(uiState = HomeFeedUiState.Loading)
        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.resources.getString(R.string.loading_home_screen))
            .assertExists()

    }

    @Test
    fun recipeList_whenDataIsLoaded_exists() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeFeedUiState.Success(
                    listOf(
                        RecipeModel(
                            1,
                            "Recipe 1",
                            "Description 1"
                        )
                    )
                )
            )
        }
        composeTestRule
            .onNodeWithText("Recipe 1")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Description 1")
            .assertIsDisplayed()
    }
}