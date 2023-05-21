package com.roshanadke.recipescompose.network

import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.data.model.RecipeListItem
import com.roshanadke.recipescompose.data.model.recipe.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RecipeService {

    companion object {
        val BASE_URL = "https://api.spoonacular.com"
    }


    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
    ): Response<RecipeDashboardList>

    @GET("/recipes/{id}/information")
    suspend fun getSingleRecipeInfo(
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean
    ): Response<Recipe>


}