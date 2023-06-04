package com.roshanadke.recipescompose.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.roshanadke.recipescompose.R
import com.roshanadke.recipescompose.data.getTemporaryRecipesList
import com.roshanadke.recipescompose.data.model.RecipeListItem
import com.roshanadke.recipescompose.domain.viewmodel.RecipesViewModel

@Composable
fun RecipesMainScreen(
    navController: NavController,
    recipesViewModel: RecipesViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hello, Guest",
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "What would you like to cook today?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        SearchBar()

        Text(
            text = "Recommendation",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                Log.d("TAG", "RecipesMainScreen:  clicked ")
                recipesViewModel.getRecipesList("pizza")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        //RecipesList(list = recipesViewModel.recipesDashboardListState.value.recipesList)

        getTemporaryRecipesList(context = context).forEach {
            Log.d("TAG", "RecipesMainScreen: ${it.title} ")
        }

        RecipesList(
            list = getTemporaryRecipesList(context),
            onItemClick = {id ->
                navController.navigate(Screen.RecipeDetailsScreen.withArgs(id.toString()))
            })

    }


}

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipesList(
    list: List<RecipeListItem>,
    onItemClick: (Int) -> Unit,
) {

    Column {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list) {

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    onClick = {
                        onItemClick(it.id)
                    }

                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = it.image,
                                builder = {
                                    crossfade(true)
                                },
                            ),
                            contentDescription = it.title,
                            modifier = Modifier
                                .height(150.dp)
                                .width(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = it.title,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )

                    }

                }
                Spacer(modifier = Modifier.height(12.dp))

            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    val searchText = remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                modifier = Modifier.padding(
                    start = 12.dp, top = 12.dp,
                    bottom = 12.dp, end = 4.dp
                ),
                contentDescription = "Search"
            )

            TextField(
                value = searchText.value,
                onValueChange = {
                    searchText.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )

            )


        }

    }

}











