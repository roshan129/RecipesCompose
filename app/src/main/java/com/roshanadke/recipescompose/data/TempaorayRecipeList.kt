package com.roshanadke.recipescompose.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.roshanadke.recipescompose.data.model.Recipe
import com.roshanadke.recipescompose.data.model.RecipeListItem
import org.json.JSONObject


fun getTemporaryRecipesList(context: Context): List<RecipeListItem> {

    try {
        val jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val results = json.getJSONArray("results")

        val recipeList = mutableListOf<RecipeListItem>()
        for (i in 0 until results.length()) {
            val recipe = results.getJSONObject(i)
            val id = recipe.getInt("id")
            val title = recipe.getString("title")
            val imageUrl = recipe.getString("image")

            recipeList.add(
                RecipeListItem(
                    id,
                    imageUrl,
                    "jpg",
                    title,
                )
            )
        }
        return recipeList
    }catch (e: Exception) {
        Log.d("TAG", "getTemporaryRecipesList exception: $e ")
    }

    return emptyList()
}

fun getSingleRecipeData(context: Context): Recipe {
    val jsonString = context.assets.open("recipe.json").bufferedReader().use { it.readText() }
    val json = JSONObject(jsonString)

    val gson = Gson()
    return gson.fromJson(json.toString(), Recipe::class.java)

}