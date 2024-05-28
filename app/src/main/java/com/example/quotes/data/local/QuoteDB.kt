package com.example.quotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quotes.core.utils.toListQuoteEntity
import com.example.quotes.data.local.daos.QuoteDAO
import com.example.quotes.data.local.entities.QuoteEntity
import com.example.quotes.domain.model.QuoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDB : RoomDatabase() {

    abstract fun quoteDao(): QuoteDAO

    companion object {
        @Volatile
        private var INSTANCE: QuoteDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): QuoteDB {
            //--- Ejecutar si la instancia no es nulo y devolver la instancia,
            //--- sino crear la base de datos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDB::class.java,
                    "quotes.dbf"
                )
                    //--- Limpia y reconstruye en lugar de migrar si no hay un objeto de migración
                    //--- Aqui no se revisa el tema de migración
                    .fallbackToDestructiveMigration()
                    .addCallback(QuoteDBCallback(scope))
                    .build()
                INSTANCE = instance
                //-- Devolver la instancia
                instance
            }
        }

        private class QuoteDBCallback(
            private val scope: CoroutineScope
        ) : Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populate(database.quoteDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more elements
         */
        suspend fun populate(quoteDao: QuoteDAO) {

            quoteDao.deleteAll()

            val quotes = listOf(
                QuoteModel(
                    id=1,
                    quote = """La mejor forma de obtener información correcta de los foros de 
                    |Usenet es enviar algo incorrecto y esperar las correcciones""".trimMargin(),
                    author = "Matthew Austern"
                ),
                QuoteModel(
                    id=2,
                    quote = """No temo a los ordenadores; lo que temo es quedarme sin ellos""",
                    author = "Isaac Asimov"
                ),
                QuoteModel(
                    id=3,
                    quote = """Pienso que los virus informáticos muestran la naturaleza humana: 
                    |la única forma de vida que hemos creado hasta el momento es puramente destructiva""".trimMargin(),
                    author = "Stephen Hawking"
                ),
                QuoteModel(
                    id=4,
                    quote = """Una vez un ordenador me venció jugando al ajedrez, pero no me opuso 
                    |resistencia cuando pasamos al kick boxing""".trimMargin(),
                    author = "Emo Philips"
                ),
                QuoteModel(
                    id=5,
                    quote = "Los estándares son siempre obsoletos. Eso es lo que los hace estándares",
                    author = "Alan Bennett"
                ),
                QuoteModel(
                    id=6,
                    quote = """El hardware es lo que hace a una máquina rápida; el software es lo que 
                    |hace que una máquina rápida se vuelva lenta""".trimMargin(),
                    author = "Craig Bruce"
                ),
                QuoteModel(
                    id=7,
                    quote = """En la actualidad nos hacen creer que ayudar a un amigo es moralmente
                    |equivalente a atacar un barco. Te llaman pirata""".trimMargin(),
                    author = "Richard Stallman"
                ),
                QuoteModel(
                    id=8,
                    quote = """Si piensas que la tecnología puede solucionar tus problemas de seguridad,
                     |está claro que ni entiendes los problemas ni entiendes la tecnología""".trimMargin(),
                    author = "Bruce Schneier"),
                QuoteModel(
                    id=9,
                    quote = "Hablar es fácil. Muéstrame el código.",
                    author = "Linus Torvalds"),
                QuoteModel(
                    id=10,
                    quote = """Enseñar a los niños el uso de software libre en las escuelas, 
                    |formará individuos con sentido de libertad""".trimMargin(),
                    author = "Richard Stallman"
                ),
                QuoteModel(
                    id=11,
                    quote = "El mejor lugar para estudiar es el ITVO",
                    author = "Academia de Informatica y Tics"),
                QuoteModel(
                    id=12,
                    quote = "El respeto al derecho ajeno es la paz",
                    author = "Benito Juárez García")
            )

            quoteDao.insertAll(quotes.toListQuoteEntity())

        }
    }
}