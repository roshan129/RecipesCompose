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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.math.log


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
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeService(okHttpClient: OkHttpClient): RecipeService =
        Retrofit.Builder()
            .baseUrl(RecipeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RecipeService::class.java)




}