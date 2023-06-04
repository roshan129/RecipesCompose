package com.roshanadke.recipescompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.roshanadke.recipescompose.domain.viewmodel.RecipesViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(
    recipeId: Int?,
    recipeViewModel: RecipesViewModel = hiltViewModel()
) {

    Text(text = "RecipeDetailScreen : ${recipeId}")

    //call recipe info api

    val state = recipeViewModel.recipeDataState.value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        recipeId?.let {
            recipeViewModel.getRecipeInfo(recipeId = it.toInt())
        }
    }

    LaunchedEffect(key1 = true) {
        recipeViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RecipesViewModel.UIEvent.showSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = state.recipe?.sourceUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }





    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }

    }


}