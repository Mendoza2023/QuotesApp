package com.example.quotes.domain.usecase

import com.example.quotes.domain.QuoteRepository
import com.example.quotes.domain.model.QuoteState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {

    suspend fun getQuotes(): Flow<QuoteState> = quoteRepository.getQuotes()

}
