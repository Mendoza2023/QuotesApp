package com.example.quotes.domain.usecase

import com.example.quotes.domain.QuoteRepository
import javax.inject.Inject

class DeleteQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun deleteQuote(quoteId: Int) {
        quoteRepository.deleteQuote(quoteId)
    }
}

