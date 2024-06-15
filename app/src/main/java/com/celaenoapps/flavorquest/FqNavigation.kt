package com.celaenoapps.flavorquest

import androidx.navigation.NavController

object FqDestinations {
    const val HOME_ROUTE = "home_route"
    const val CATEGORY_ROUTE = "category_route"
    const val FAVORITE_ROUTE = "favorite_route"
}

/**
 * Models the navigation actions in the app.
 */

class FqNavigationActions(private val navController: NavController) {
    fun navigateToTopLevelDestination(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            navController.graph.startDestinationRoute?.let {
                popUpTo(it)
            }
        }
    }
}