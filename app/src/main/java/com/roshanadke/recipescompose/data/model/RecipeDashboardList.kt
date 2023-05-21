package com.roshanadke.recipescompose.data.model

data class RecipeDashboardList(
    val recipeListItem: List<RecipeListItem>,
    val number: Int,
    val offset: Int,
    val totalResults: Int
)