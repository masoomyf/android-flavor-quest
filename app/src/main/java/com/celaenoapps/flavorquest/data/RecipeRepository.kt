package com.celaenoapps.flavorquest.data

import kotlinx.coroutines.flow.Flow

/**
 * Data layer implementation for the [RecipeModel]
 */
interface RecipeRepository {
    /**
     * Get a list of [RecipeModel] from the data source
     */
    fun getRecipes(): Flow<List<RecipeModel>>
}