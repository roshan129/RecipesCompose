package com.roshanadke.recipescompose.domain.use_case

import com.roshanadke.recipescompose.common.Resource
import com.roshanadke.recipescompose.data.model.Recipe
import com.roshanadke.recipescompose.domain.repo.RecipesRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(
    private val repository: RecipesRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Recipe>> {
        return repository.getSingleRecipeInfo(id)
    }
}
