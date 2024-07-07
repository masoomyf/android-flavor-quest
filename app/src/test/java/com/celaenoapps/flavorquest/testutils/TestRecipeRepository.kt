package com.celaenoapps.flavorquest.testutils

import com.celaenoapps.flavorquest.data.RecipeModel
import com.celaenoapps.flavorquest.data.RecipeRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


class TestRecipeRepository : RecipeRepository {
    /**
     * The backing hot flow for the list of recipe ids for testing.
     */
    private val recipeFlow = MutableSharedFlow<List<RecipeModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun getRecipes(): Flow<List<RecipeModel>> {
        return recipeFlow
    }

    /**
     * A test-only API to allow controlling the list of recipe resources from tests.
     */

    fun setRecipes(recipes: List<RecipeModel>) {
        recipeFlow.tryEmit(recipes)
    }

}