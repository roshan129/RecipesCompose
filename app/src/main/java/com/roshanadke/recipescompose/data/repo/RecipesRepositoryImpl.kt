package com.roshanadke.recipescompose.data.repo

import android.util.Log
import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.data.network.RecipeService
import com.roshanadke.recipescompose.domain.repo.RecipesRepository
import com.roshanadke.recipescompose.common.Resource
import com.roshanadke.recipescompose.data.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception

class RecipesRepositoryImpl(
    private val recipeService: RecipeService
) : RecipesRepository {

    override fun getRecipes(query: String): Flow<Resource<RecipeDashboardList>> = flow {
        emit(Resource.Loading())
        try {
            val recipes = recipeService.getRecipes(query = query)
            emit(Resource.Success(recipes))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Please check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Some error occurred"))
        }
    }

    override fun getSingleRecipeInfo(id: Int): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            val recipeInfo = recipeService.getSingleRecipeInfo(id, includeNutrition = false)
            emit(Resource.Success(data = recipeInfo))
        }catch (e: IOException) {
            emit(Resource.Error(message = "Please check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Some error occurred"))
        }
    }
}