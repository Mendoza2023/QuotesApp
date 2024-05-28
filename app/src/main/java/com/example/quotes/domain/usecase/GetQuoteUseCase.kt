package com.example.quotes.domain.usecase

import com.example.quotes.domain.QuoteRepository
import com.example.quotes.domain.model.QuoteModel
import com.example.quotes.domain.model.QuoteState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun getQuote(quoteId: Int): Flow<QuoteState> = quoteRepository.getQuote(quoteId)

}