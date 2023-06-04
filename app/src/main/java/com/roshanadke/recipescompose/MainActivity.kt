package com.roshanadke.recipescompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.roshanadke.recipescompose.common.Navigation
import com.roshanadke.recipescompose.domain.viewmodel.RecipesViewModel
import com.roshanadke.recipescompose.presentation.RecipesMainScreen
import com.roshanadke.recipescompose.ui.theme.RecipesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Log.d("TAG", "onCreate: app start")


            RecipesComposeTheme {

                val viewModel: RecipesViewModel = hiltViewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Navigation()


                }
            }
        }
    }
}

