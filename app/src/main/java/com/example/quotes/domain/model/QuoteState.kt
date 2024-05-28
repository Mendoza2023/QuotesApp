package com.example.quotes.domain.model

sealed class QuoteState {
    data object Init: QuoteState()
    data object Loading: QuoteState()
    class Data(val quotes: List<QuoteModel>): QuoteState()
    class Error(val error: Throwable): QuoteState()
    class Success(val message: String): QuoteState()
}