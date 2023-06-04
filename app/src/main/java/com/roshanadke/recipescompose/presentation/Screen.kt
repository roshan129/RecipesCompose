package com.roshanadke.recipescompose.presentation

sealed class Screen(val route: String) {
    object RecipeMainScreen: Screen("RecipeMainScreen")
    object RecipeDetailsScreen: Screen("RecipeListScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }

    }
}