package com.celaenoapps.flavorquest.data

import kotlinx.serialization.Serializable

/**
 * Recipe model
 */
@Serializable
data class RecipeModel(
    val id: Int,
    val title: String,
    val description: String,
)
