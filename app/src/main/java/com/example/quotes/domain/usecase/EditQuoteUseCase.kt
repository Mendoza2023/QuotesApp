package com.example.quotes.domain.usecase

import com.example.quotes.domain.QuoteRepository
import com.example.quotes.domain.model.QuoteModel
import javax.inject.Inject

class EditQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun editQuote(quoteModel: QuoteModel) {
        quoteRepository.editQuote(quoteModel)
    }
}
