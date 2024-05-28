package com.example.quotes.data.local

import com.example.quotes.domain.model.QuoteModel
import com.example.quotes.domain.model.QuoteState
import kotlinx.coroutines.flow.Flow

interface QuoteLocalDataSource {
    suspend fun getQuotes(): Flow<QuoteState>
    suspend fun getQuote(quoteId: Int): Flow<QuoteState>
    suspend fun getQuoteRandom(): Flow<QuoteState>
    suspend fun insertAll(quotes: List<QuoteModel>)
    suspend fun insert(quoteModel: QuoteModel)
    suspend fun editQuote(quoteModel: QuoteModel)
    suspend fun deleteQuote(quoteId: Int) // Método para eliminar una cita
}
