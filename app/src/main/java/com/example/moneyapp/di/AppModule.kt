package com.example.moneyapp.di

import com.example.moneyapp.Calculator
import com.example.moneyapp.CompoundCalculatorModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCalculator(): Calculator = CompoundCalculatorModel()

    @Provides
    @Singleton
    fun provideCompoundCalculatorModel() = CompoundCalculatorModel()
}