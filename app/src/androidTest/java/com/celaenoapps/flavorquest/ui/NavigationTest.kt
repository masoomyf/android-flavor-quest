package com.celaenoapps.flavorquest.ui

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import com.celaenoapps.flavorquest.MainActivity
import org.junit.Rule
import org.junit.Test

/**
 * Test all the navigation flows that are handled by the navigation library.
 */
class NavigationTest {
    /**
     * Using an primary activity that has control of the content
     */

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun firstScreenIsHomeScreen() {
        composeTestRule.onNodeWithTag("HomeScreen").assertExists(
            "Could not found the home screen."
        )
    }

    @Test
    fun navigateToCategoryScreen() {
        composeTestRule.findCategoryNavItemNode().performClick()
        composeTestRule.onNodeWithTag("CategoryScreen").assertExists(
            "Could not found the category screen."
        )
    }

    @Test
    fun navigateToFavoriteScreen() {
        composeTestRule.findFavoriteNavItemNode().performClick()
        composeTestRule.onNodeWithTag("FavoriteScreen").assertExists(
            "Could not found the favorite screen."
        )
    }

    @Test
    fun topLevelDestinationsDoNotShowUpArrow() {
        composeTestRule.onNodeWithContentDescription("Navigate up").assertDoesNotExist()
        composeTestRule.findFavoriteNavItemNode().performClick()
        composeTestRule.onNodeWithContentDescription("Navigate up").assertDoesNotExist()
        composeTestRule.findCategoryNavItemNode().performClick()
        composeTestRule.onNodeWithContentDescription("Navigate up").assertDoesNotExist()
    }

    /*
    * There should always be at most one instance of a top-level destination at the same time.
    */
    @Test(expected = NoActivityResumedException::class)
    fun backFromHomeQuitsApp() {
        // user navigate to category screen
        composeTestRule.findCategoryNavItemNode().performClick()
        // user navigate to homeScreen
        composeTestRule.findHomeNavItemNode().performClick()
        // user press back button/ or from gesture
        Espresso.pressBack()
        // App should quit
    }

    /*
    * When pressing back from any top level destination except "For you", the app navigates back
    * to the "For you" destination, no matter which destinations you visited in between.
    */
    @Test
    fun backFromDestinationsReturnsToHome() {
        composeTestRule.findFavoriteNavItemNode().performClick()
        composeTestRule.findCategoryNavItemNode().performClick()
        // WHEN the user uses the system button/gesture to go back,
        Espresso.pressBack()

        // THEN the app navigates back to the home screen
        composeTestRule.onNodeWithTag("HomeScreen").assertExists()
    }


    private fun ComposeTestRule.findHomeNavItemNode() = onNodeWithText("Home")
    private fun ComposeTestRule.findCategoryNavItemNode() = onNodeWithText("Category")
    private fun ComposeTestRule.findFavoriteNavItemNode() = onNodeWithText("Favorite")


}