package com.celaenoapps.flavorquest.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.celaenoapps.flavorquest.R
import com.celaenoapps.flavorquest.data.RecipeModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun HomeScreen(
    uiState: HomeFeedUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            HomeFeedUiState.Loading -> {
                val contentDesc = stringResource(R.string.loading_home_screen)
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .semantics {
                            contentDescription = contentDesc
                        },
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is HomeFeedUiState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.recipes, key = { it.id }, contentType = { "recipe_card" }) {
                        RecipeCard(recipe = it)
                    }
                }
            }

            is HomeFeedUiState.Error -> {}
        }
    }
}

@Composable
private fun RecipeCard(
    recipe: RecipeModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = recipe.title
            )
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeFeedUiState.Success(
            recipes = listOf(
                RecipeModel(1, "Recipe 1", "Description 1"),
                RecipeModel(2, "Recipe 2", "Description 2"),
                RecipeModel(3, "Recipe 3", "Description 3"),
            )
        )
    )
}