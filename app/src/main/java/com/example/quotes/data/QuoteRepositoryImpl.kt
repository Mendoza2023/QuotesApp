package com.example.quotes.data

import android.util.Log
import com.example.quotes.data.local.QuoteLocalDataSource
import com.example.quotes.domain.QuoteRepository
import com.example.quotes.domain.model.QuoteModel
import com.example.quotes.domain.model.QuoteState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val localDataSource: QuoteLocalDataSource
) : QuoteRepository {

    override suspend fun getQuoteRandom(): Flow<QuoteState> {
        return localDataSource.getQuoteRandom()
    }

    override suspend fun getQuote(quoteId: Int): Flow<QuoteState> {
        return localDataSource.getQuote(quoteId)
    }

    override suspend fun getQuotes(): Flow<QuoteState> {
        Log.e("REPO", "Ejecutando getQuotes")
        val localQuotes = localDataSource.getQuotes()
        return localQuotes
    }

    override suspend fun editQuote(quoteModel: QuoteModel) {
        return localDataSource.editQuote(quoteModel)
    }

    override suspend fun addQuote(quoteModel: QuoteModel) {
        return localDataSource.insert(quoteModel)
    }

    override suspend fun deleteQuote(quoteId: Int) {
        return localDataSource.deleteQuote(quoteId)
    }
}

