package com.celaenoapps.flavorquest.di

import com.celaenoapps.flavorquest.data.RecipeRepository
import com.celaenoapps.flavorquest.data.fake.FakeRecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {


    /**
     * Binds the [FakeRecipeRepository] to the [RecipeRepository] interface using the Hilt dependency injection framework.
     *
     * @param recipeRepository The instance of [FakeRecipeRepository] to bind.
     * @return The bound [RecipeRepository] interface.
     */
    @Binds
    fun bindRecipeRepository(recipeRepository: FakeRecipeRepository): RecipeRepository

    /**
     * Binds the [DefaultFqDispatchers] implementation to the [FqDispatchers] interface.
     * This allows Dagger to inject the [FqDispatchers] dependency wherever it is needed.
     *
     * @param defaultFqDispatchers The [DefaultFqDispatchers] instance to bind.
     * @return The bound [FqDispatchers] instance.
     */
    @Binds
    fun bindFqDispatchers(defaultFqDispatchers: DefaultFqDispatchers): FqDispatchers

    companion object {
        /**
         * @return A Json object with the ignoreUnknownKeys flag set to true.
         */
        @Provides
        @Singleton
        fun provideNetworkJson(): Json {
            return Json { ignoreUnknownKeys = true }
        }
    }
}