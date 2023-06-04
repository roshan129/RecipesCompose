package com.roshanadke.recipescompose.domain.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshanadke.recipescompose.data.model.RecipeListItem
import com.roshanadke.recipescompose.data.network.RecipeService
import com.roshanadke.recipescompose.domain.use_case.GetRecipeListUseCase
import com.roshanadke.recipescompose.presentation.RecipesDashboardDataState
import com.roshanadke.recipescompose.common.Resource
import com.roshanadke.recipescompose.domain.use_case.GetRecipeUseCase
import com.roshanadke.recipescompose.presentation.RecipeDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val recipeService: RecipeService
) : ViewModel() {


    private var _recipesList: MutableState<List<RecipeListItem>> = mutableStateOf(emptyList())
    val recipesList: State<List<RecipeListItem>> = _recipesList

    private var _recipesDashboardListState: MutableState<RecipesDashboardDataState> =
        mutableStateOf(RecipesDashboardDataState())
    val recipesDashboardListState: State<RecipesDashboardDataState> = _recipesDashboardListState

    private var _recipeDataState: MutableState<RecipeDataState> =
        mutableStateOf(RecipeDataState())
    val recipeDataState: State<RecipeDataState> = _recipeDataState

    private var _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UIEvent {
        data class showSnackBar(val message: String): UIEvent()
    }


    init {
        //getRecipesList()
    }

    fun getRecipesList(searchQuery: String = "") {
        Log.d("TAG", "getRecipesList: viewmodel")
        viewModelScope.launch {

            /* val recipees = recipeService.getRecipes(searchQuery)
             Log.d("TAG", "getRecipesList: ${recipees.toString()}")
             Log.d("TAG", "getRecipesList: items:  ${recipees?.recipeListItem?.size}")
 */
            getRecipeListUseCase(searchQuery).onEach { result ->
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
                        _recipesDashboardListState.value =
                            _recipesDashboardListState.value.copy(
                                isLoading = false
                            )
                        _eventFlow.emit(UIEvent.showSnackBar(
                            message = result.message ?: "Unknown Error"
                        ))
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

    fun getRecipeInfo(recipeId: Int) {
        viewModelScope.launch {
            getRecipeUseCase(recipeId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _recipeDataState.value = recipeDataState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _recipeDataState.value = recipeDataState.value.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.showSnackBar(
                            result.message ?: "Unknown Error"
                        ))
                    }
                    is Resource.Success -> {
                        _recipeDataState.value = recipeDataState.value.copy(
                            isLoading = false,
                            recipe = result.data
                        )
                    }
                }

            }.launchIn(this)
        }
    }


}