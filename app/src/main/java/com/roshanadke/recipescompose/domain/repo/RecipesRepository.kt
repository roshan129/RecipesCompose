package com.roshanadke.recipescompose.domain.repo

import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.common.Resource
import com.roshanadke.recipescompose.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(query: String): Flow<Resource<RecipeDashboardList>>
    fun getSingleRecipeInfo(id: Int): Flow<Resource<Recipe>>
}