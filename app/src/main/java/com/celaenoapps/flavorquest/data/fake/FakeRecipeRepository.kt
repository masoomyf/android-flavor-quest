package com.celaenoapps.flavorquest.data.fake

import com.celaenoapps.flavorquest.data.RecipeModel
import com.celaenoapps.flavorquest.data.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * [RecipeRepository] implementation that provides static recipes resources to aid development
 */
class FakeRecipeRepository(
    private val dispatcher: CoroutineDispatcher
): RecipeRepository {
    private val deserializer = Json { ignoreUnknownKeys = true}

    override fun getRecipes(): Flow<List<RecipeModel>> {
        return flow {
            emit(
                deserializer.decodeFromString<ResourceData>(FakeDataSource.data).resources
            )
        }.flowOn(dispatcher)
    }
}

@Serializable
private data class ResourceData(
    val resources: List<RecipeModel>
)