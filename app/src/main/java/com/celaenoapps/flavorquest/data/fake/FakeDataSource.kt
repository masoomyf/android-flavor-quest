package com.celaenoapps.flavorquest.data.fake

import com.celaenoapps.flavorquest.data.RecipeModel

object FakeDataSource {
    val recipeList = listOf<RecipeModel>(
        RecipeModel(
            id = 1,
            title = "Pizza",
            description = "A delicious pizza with cheese and tomato sauce",
        ),
        RecipeModel(
            id = 2,
            title = "Pasta",
            description = "A delicious pasta with tomato sauce and cheese",
        ),
        RecipeModel(
            id = 3,
            title = "Salad",
            description = "A delicious salad with mixed vegetables and cheese",
        ),
        RecipeModel(
            id = 4,
            title = "Steak",
            description = "A delicious steak with steak sauce and cheese",
        ),
        RecipeModel(
            id = 5,
            title = "Soup",
            description = "A delicious soup with mixed vegetables and cheese",
        ),
        RecipeModel(
            id = 6,
            title = "Sushi",
            description = "A delicious sushi with sushi sauce and cheese",
        ),
        RecipeModel(
            id = 7,
            title = "Tacos",
            description = "A delicious taco with mixed vegetables and cheese",
        ),
        RecipeModel(
            id = 8,
            title = "Hamburger",
            description = "A delicious hamburger with hamburger sauce and cheese",
        ),
    )
}