package com.roshanadke.recipescompose.data.model

import com.google.gson.annotations.SerializedName

data class RecipeDashboardList(
    @SerializedName("results")
    val recipeListItem: List<RecipeListItem>,
    val number: Int,
    val offset: Int,
    val totalResults: Int
)