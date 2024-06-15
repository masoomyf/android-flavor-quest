package com.celaenoapps.flavorquest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FqNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = FqDestinations.HOME_ROUTE
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(FqDestinations.HOME_ROUTE) {
            Text(text = "Home Screen", modifier = modifier.testTag("HomeScreen"))
        }

        composable(FqDestinations.CATEGORY_ROUTE) {
            Text(text = "Category Screen", modifier = modifier.testTag("CategoryScreen"))
        }

        composable(FqDestinations.FAVORITE_ROUTE) {
            Text(text = "Favorite Screen", modifier = modifier.testTag("FavoriteScreen"))
        }
    }
}