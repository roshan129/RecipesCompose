package com.roshanadke.recipescompose.di

import androidx.lifecycle.ViewModel
import com.roshanadke.recipescompose.network.RecipeService
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
    fun provideRecipeService(): RecipeService =
        Retrofit.Builder()
            .baseUrl(RecipeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)


}