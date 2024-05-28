package com.example.quotes.domain.usecase

import com.example.quotes.domain.QuoteRepository
import com.example.quotes.domain.model.QuoteModel
import javax.inject.Inject

class AddQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun addQuote(quoteModel: QuoteModel) =
        quoteRepository.addQuote(quoteModel)
}