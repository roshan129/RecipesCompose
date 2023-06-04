package com.roshanadke.recipescompose.domain.use_case

import com.roshanadke.recipescompose.data.model.RecipeDashboardList
import com.roshanadke.recipescompose.domain.repo.RecipesRepository
import com.roshanadke.recipescompose.common.Resource
import com.roshanadke.recipescompose.data.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipeListUseCase(
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