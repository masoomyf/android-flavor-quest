package com.celaenoapps.flavorquest.data.fake

import com.celaenoapps.flavorquest.data.RecipeModel
import com.celaenoapps.flavorquest.data.RecipeRepository
import com.celaenoapps.flavorquest.di.FqDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * [RecipeRepository] implementation that provides static recipes resources to aid development
 */
class FakeRecipeRepository @Inject constructor(
    private val dispatcher: FqDispatchers,
    private val networkJson: Json
): RecipeRepository {
    override fun getRecipes(): Flow<List<RecipeModel>> {
        return flow {
            // Perform fake delay.
            kotlinx.coroutines.delay(1000)
            emit(
                networkJson.decodeFromString<ResourceData>(FakeDataSource.data).resources
            )
        }.flowOn(dispatcher.io)
    }
}

@Serializable
private data class ResourceData(
    val resources: List<RecipeModel>
)