package com.roshanadke.recipescompose.domain.use_case

import android.util.Log
import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.domain.repo.RecipesRepository
import com.roshanadke.recipescompose.util.Resource
import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(
    private val recipesRepository: RecipesRepository
) {

    operator fun invoke(query: String): Flow<Resource<RecipeDashboardList>> {
        Log.d("TAG", "invoke: invoked")
        return recipesRepository.getRecipes(query = query)
    }

}