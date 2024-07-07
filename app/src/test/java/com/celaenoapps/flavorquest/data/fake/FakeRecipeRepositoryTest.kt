package com.celaenoapps.flavorquest.data.fake

import com.celaenoapps.flavorquest.di.DefaultFqDispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before

class FakeRecipeRepositoryTest {
    private lateinit var subject: FakeRecipeRepository

    @Before
    fun setup() {
        subject = FakeRecipeRepository(
            dispatcher = DefaultFqDispatchers(),
            networkJson = Json { ignoreUnknownKeys = true }
        )
    }

    @org.junit.Test
    fun recipeList() = runTest {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getRecipes().first().first()
        )
    }
}