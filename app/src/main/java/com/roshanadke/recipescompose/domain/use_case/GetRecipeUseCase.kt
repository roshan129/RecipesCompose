package com.roshanadke.recipescompose.domain.use_case

import android.util.Log
import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.domain.repo.RecipesRepository
import com.roshanadke.recipescompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipeUseCase(
    private val recipesRepository: RecipesRepository,
) {

    operator fun invoke(query: String): Flow<Resource<RecipeDashboardList>> {

        if (query.isBlank()) {
            return flow {  }
        }
        val flowOne = recipesRepository.getRecipes(query = query)

        return flowOne
    }

}