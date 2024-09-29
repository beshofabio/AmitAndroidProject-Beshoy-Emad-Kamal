package com.example.amitproject.di

import com.example.domain.repo.mainscreen.MainScreenRepo
import com.example.domain.usecase.mainscreen.GetMealsFromRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(mainScreenRepo: MainScreenRepo): GetMealsFromRemote {
        return GetMealsFromRemote(mainScreenRepo)
    }
}