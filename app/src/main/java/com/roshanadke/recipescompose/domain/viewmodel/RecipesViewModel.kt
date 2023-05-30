package com.roshanadke.recipescompose.domain.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshanadke.recipescompose.BuildConfig
import com.roshanadke.recipescompose.data.model.RecipeListItem
import com.roshanadke.recipescompose.data.network.RecipeService
import com.roshanadke.recipescompose.domain.use_case.GetRecipeUseCase
import com.roshanadke.recipescompose.presentation.RecipesDashboardDataState
import com.roshanadke.recipescompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
    private val recipeService: RecipeService
) : ViewModel() {


    private var _recipesList: MutableState<List<RecipeListItem>> = mutableStateOf(emptyList())
    val recipesList: State<List<RecipeListItem>> = _recipesList

    private var _recipesDashboardListState: MutableState<RecipesDashboardDataState> =
        mutableStateOf(RecipesDashboardDataState())
    val recipesDashboardListState: State<RecipesDashboardDataState> = _recipesDashboardListState



    init {
        getRecipesList()
    }

    fun getRecipesList(searchQuery: String = "") {
        Log.d("TAG", "getRecipesList: viewmodel")
        viewModelScope.launch {

           /* val recipees = recipeService.getRecipes(searchQuery)
            Log.d("TAG", "getRecipesList: ${recipees.toString()}")
            Log.d("TAG", "getRecipesList: items:  ${recipees?.recipeListItem?.size}")
*/
            getRecipeUseCase(searchQuery).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.d("TAG", "getRecipesList: success")
                        _recipesDashboardListState.value =
                            _recipesDashboardListState.value.copy(
                                recipesList = result.data?.recipeListItem ?: emptyList(),
                                isLoading = false
                            )
                    }

                    is Resource.Error -> {
                        Log.d("TAG", "getRecipesList: error")
                        _recipesDashboardListState.value =
                            _recipesDashboardListState.value.copy(
                                isLoading = false
                            )
                    }

                    is Resource.Loading -> {
                        Log.d("TAG", "getRecipesList: error 2")
                        _recipesDashboardListState.value =
                            _recipesDashboardListState.value.copy(
                                recipesList = result.data?.recipeListItem ?: emptyList(),
                                isLoading = true
                            )
                    }
                }
            }.launchIn(this)


        }
    }


}