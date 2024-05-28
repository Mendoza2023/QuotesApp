package com.example.quotes.core.di

import com.example.quotes.data.QuoteRepositoryImpl
import com.example.quotes.data.local.QuoteLocalDataSource
import com.example.quotes.data.local.QuoteLocalDataSourceImpl
import com.example.quotes.domain.QuoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class QuoteRepositoryModule {

    @Binds
    abstract fun bindQuoteRepository(quoteRepositoryImpl: QuoteRepositoryImpl):
            QuoteRepository

    @Binds
    abstract fun bindQuoteLocalDataSource(quoteLocalDataSourceImpl: QuoteLocalDataSourceImpl):
            QuoteLocalDataSource

}