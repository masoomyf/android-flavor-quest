package com.celaenoapps.flavorquest

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.celaenoapps.flavorquest.ui.theme.FlavorQuestTheme

@Composable
fun FlavorQuestApp(modifier: Modifier = Modifier) {
    FlavorQuestTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            FqNavigationActions(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route ?: FqDestinations.HOME_ROUTE

        Scaffold(
            modifier = modifier,
            bottomBar = {
                FqBottomBar(navigationActions, currentRoute)
            },
        ) { padding ->
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                FqNavGraph(
                    navHostController = navController,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
fun FqBottomBar(
    navigationActions: FqNavigationActions,
    currentRoute: String
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        NavigationBar(tonalElevation = 0.dp) {
            TOP_LEVEL_DESTINATIONS.forEach {
                val selected = currentRoute == it.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { navigationActions.navigateToTopLevelDestination(it.route) },
                    icon = {
                        val icon = if (selected) it.selectedIcon else it.unselectedIcon
                        Icon(
                            imageVector = icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = stringResource(id = it.textId))
                    }
                )
            }
        }
    }
}


private sealed class Destination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val textId: Int
) {
    data object Home : Destination(
        route = FqDestinations.HOME_ROUTE,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        textId = R.string.home
    )

    data object Category : Destination(
        route = FqDestinations.CATEGORY_ROUTE,
        selectedIcon = Icons.Filled.Category,
        unselectedIcon = Icons.Outlined.Category,
        textId = R.string.category
    )

    data object Favorite : Destination(
        route = FqDestinations.FAVORITE_ROUTE,
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        textId = R.string.favorite
    )
}

private val TOP_LEVEL_DESTINATIONS = listOf(
    Destination.Home,
    Destination.Category,
    Destination.Favorite
)