package com.roshanadke.recipescompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.roshanadke.recipescompose.data.model.RecipeListItem
import com.roshanadke.recipescompose.domain.viewmodel.RecipesViewModel

@Composable
fun RecipesMainScreen(
    recipesViewModel: RecipesViewModel
) {


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
            fontWeight = FontWeight.Bold
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(recipesViewModel.recipesDashboardListState.value.recipesList) {
                Text(text = "${it.title}")
            }
        }

    }


}

@Composable
fun RecipesList(
    list: List<RecipeListItem>
) {

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











