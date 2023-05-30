package com.roshanadke.recipescompose.domain.repo

import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.data.model.RecipeListItem
import com.roshanadke.recipescompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(query: String): Flow<Resource<RecipeDashboardList>>
}