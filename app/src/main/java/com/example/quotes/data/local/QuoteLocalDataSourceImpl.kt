package com.example.quotes.data.local


import com.example.quotes.core.utils.toEntity
import com.example.quotes.core.utils.toListQuoteModel
import com.example.quotes.core.utils.toQuoteModel
import com.example.quotes.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.quotes.data.local.daos.QuoteDAO
import com.example.quotes.domain.model.QuoteState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

@Singleton
class QuoteLocalDataSourceImpl  @Inject constructor(private val quoteDao: QuoteDAO): QuoteLocalDataSource {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getQuotes(): Flow<QuoteState> {
        return try {
            quoteDao.getQuotes().flatMapConcat { it ->
                flowOf(QuoteState.Data(it.toListQuoteModel()))
            }
        } catch (ex: Exception ){
            flow { emit(QuoteState.Error(ex)) }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getQuote(quoteId: Int): Flow<QuoteState> {
        return try {
            quoteDao.getQuote(quoteId).flatMapConcat {it ->
                flowOf(QuoteState.Data(listOf(it.toQuoteModel())))
            }

        } catch (ex: Exception ){
            flow { emit(QuoteState.Error(ex)) }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getQuoteRandom(): Flow<QuoteState> {
        return try {
            quoteDao.getQuoteRandom().flatMapConcat {it ->
                flowOf(QuoteState.Data(listOf(it.toQuoteModel())))
            }

        } catch (ex: Exception ){
            flow { emit(QuoteState.Error(ex)) }
        }
    }

    override suspend fun insertAll(quotes: List<QuoteModel>) {
        quoteDao.insertAll(quotes.map { it.toEntity()})
    }

    override suspend fun insert(quoteModel: QuoteModel) {
        quoteDao.insert(quoteModel.toEntity())
    }

    override suspend fun editQuote(quoteModel: QuoteModel) {
        quoteDao.updateQuote(quoteModel.toEntity())
    }
    override suspend fun deleteQuote(quoteId: Int) {
        quoteDao.delete(quoteId)
    }
}