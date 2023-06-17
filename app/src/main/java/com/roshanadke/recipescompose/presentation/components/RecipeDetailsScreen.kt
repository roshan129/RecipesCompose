package com.roshanadke.recipescompose.presentation.components

import android.text.Html
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.roshanadke.recipescompose.data.getSingleRecipeData
import com.roshanadke.recipescompose.data.network.RecipeService
import com.roshanadke.recipescompose.domain.viewmodel.RecipesViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(
    recipeId: Int?,
    recipeViewModel: RecipesViewModel = hiltViewModel()
) {


    //call recipe info api

    val state = recipeViewModel.recipeDataState.value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        recipeId?.let {
            recipeViewModel.getRecipeInfo(recipeId = it)
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

    //state.recipe?.image

    Box(modifier = Modifier.fillMaxSize()) {

            val recipe = state.recipe

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {


                item {
                    recipe?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = rememberImagePainter(
                                data = it.image,
                                builder = {
                                    crossfade(true)
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(250.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = it.title,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "${it.readyInMinutes} min",
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(32.dp))
                            Text(
                                text = if (it.vegetarian) "Veg" else "Non-veg",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Description",
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = Html.fromHtml(it.summary, Html.FROM_HTML_MODE_LEGACY).toString(),
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ingredients",
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                items(recipe?.extendedIngredients ?: emptyList()) { ingredient ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(12.dp)
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Log.d(
                                    "TAG",
                                    "IngredientsList url: ${RecipeService.getIngredientsImgUrl(ingredient.image)}"
                                )

                                Image(
                                    painter = rememberImagePainter(
                                        data = RecipeService.getIngredientsImgUrl(ingredient.image),
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.width(50.dp).height(50.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = ingredient.original,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
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


