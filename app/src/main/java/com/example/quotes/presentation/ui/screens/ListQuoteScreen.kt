package com.example.quotes.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quotes.domain.model.QuoteState
import com.example.quotes.presentation.ui.composables.CardText
import com.example.quotes.presentation.ui.composables.CircularProgressBar
import com.example.quotes.presentation.ui.composables.QuoteAction
import com.example.quotes.presentation.ui.composables.QuoteList
import com.example.quotes.presentation.viewmodel.QuoteViewModel
import kotlinx.coroutines.flow.Flow


@Composable
fun ListQuoteScreen( quotes: Flow<QuoteState>,  viewModel: QuoteViewModel) {
    val quoteState: QuoteState
            by quotes.collectAsStateWithLifecycle(initialValue = QuoteState.Loading)

    when (quoteState) {
        is QuoteState.Init -> QuoteAction(
            fontSize = 18.sp,
            text = "Listo para la acción",
            TextAlign.Center,
            listQuotesOnClick= {viewModel.getQuotes()},
        )
        is QuoteState.Loading -> CircularProgressBar(strokeWidth = 4.dp)
        is QuoteState.Data -> QuoteList(quotes = ((quoteState as QuoteState.Data).quotes),viewModel = viewModel)
        is QuoteState.Error -> CardText( fontSize = 16.sp, text = "ERROR:  " +  (quoteState as QuoteState.Error), TextAlign.Center)
        is QuoteState.Success ->  CardText( fontSize = 16.sp, text = (quoteState as QuoteState.Success).message, TextAlign.Center)
    }
}