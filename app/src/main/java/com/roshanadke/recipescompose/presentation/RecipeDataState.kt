package com.roshanadke.recipescompose.presentation

import com.roshanadke.recipescompose.data.model.Recipe

data class RecipeDataState(
    var isLoading: Boolean = false,
    var recipe: Recipe? = null
)
