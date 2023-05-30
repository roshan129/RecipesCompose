package com.roshanadke.recipescompose.presentation

import com.roshanadke.recipescompose.data.model.RecipeListItem

data class RecipesDashboardDataState(
    val recipesList: List<RecipeListItem> = emptyList(),
    val isLoading: Boolean = false
)