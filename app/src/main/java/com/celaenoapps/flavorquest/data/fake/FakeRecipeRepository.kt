package com.celaenoapps.flavorquest.data.fake

import com.celaenoapps.flavorquest.data.RecipeModel
import com.celaenoapps.flavorquest.data.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * [RecipeRepository] implementation that provides static recipes resources to aid development
 */
class FakeRecipeRepository(
    private val dispatcher: CoroutineDispatcher
): RecipeRepository {
    override fun getRecipes(): Flow<List<RecipeModel>> {
        return flow {
            emit(FakeDataSource.recipeList)
        }
    }
}