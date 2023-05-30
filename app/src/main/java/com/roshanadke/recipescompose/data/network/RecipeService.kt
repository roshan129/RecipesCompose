package com.roshanadke.recipescompose.data.network

import com.roshanadke.recipescompose.BuildConfig
import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.data.model.Recipe
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
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeDashboardList

    @GET("/recipes/{id}/information")
    suspend fun getSingleRecipeInfo(
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean
    ): Recipe


}