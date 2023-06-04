package com.roshanadke.recipescompose.di

import com.roshanadke.recipescompose.data.network.RecipeService
import com.roshanadke.recipescompose.data.repo.RecipesRepositoryImpl
import com.roshanadke.recipescompose.domain.repo.RecipesRepository
import com.roshanadke.recipescompose.domain.use_case.GetRecipeListUseCase
import com.roshanadke.recipescompose.domain.use_case.GetRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RecipesModule {

    @Provides
    @Singleton
    fun provideGetRecipesListUseCase(
        recipesRepository: RecipesRepository
    ): GetRecipeListUseCase {
        return GetRecipeListUseCase(recipesRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeUseCase(
        recipesRepository: RecipesRepository
    ): GetRecipeUseCase {
        return GetRecipeUseCase(recipesRepository)
    }

    @Provides
    @Singleton
    fun provideRecipesRepository(
        recipeService: RecipeService
    ): RecipesRepository {
        return RecipesRepositoryImpl(recipeService = recipeService)
    }


    @Provides
    @Singleton
    fun provideRecipeService(): RecipeService =
        Retrofit.Builder()
            .baseUrl(RecipeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)




}