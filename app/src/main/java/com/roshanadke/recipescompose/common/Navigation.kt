package com.roshanadke.recipescompose.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.roshanadke.recipescompose.presentation.RecipesMainScreen
import com.roshanadke.recipescompose.presentation.components.RecipeDetailsScreen
import com.roshanadke.recipescompose.presentation.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.RecipeMainScreen.route) {
        composable(route = Screen.RecipeMainScreen.route) {
            RecipesMainScreen(navController = navController)
        }

        composable(
            route = Screen.RecipeDetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { navBackStackEntry ->
            val recipeId = navBackStackEntry.arguments?.getInt("id")
            RecipeDetailsScreen(recipeId)
        }
    }


}