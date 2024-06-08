package com.celaenoapps.flavorquest.data.fake

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

class FakeRecipeRepositoryTest {
    private lateinit var subject: FakeRecipeRepository

    @Before
    fun setup() {
        subject = FakeRecipeRepository(Dispatchers.Unconfined)
    }

    @org.junit.Test
    fun recipeList() = runBlocking {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getRecipes().first().first()
        )
    }
}