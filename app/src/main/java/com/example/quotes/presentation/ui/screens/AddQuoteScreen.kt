package com.example.quotes.presentation.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quotes.presentation.viewmodel.QuoteViewModel

@Composable
fun AddQuoteScreen(
    viewModel: QuoteViewModel,
    navController: NavController,
    quoteId: Int
) {
    val quoteModel by viewModel.quoteModel.collectAsState()
    val context = LocalContext.current

    viewModel.loadQuote(quoteId)  // Cargar la cita si es necesario

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = quoteModel.id.toString(),
                onValueChange = {
                    // Solo permitir números
                    val newValue = it.filter { char -> char.isDigit() }
                    viewModel.setId(newValue.toIntOrNull() ?: 0)
                },
                label = { Text("Id: ") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = quoteModel.quote,
                onValueChange = { viewModel.setQuote(it) },
                label = { Text("Cita: ") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = quoteModel.author,
                onValueChange = { viewModel.setAuthor(it) },
                label = { Text("Autor: ") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.saveQuote()
                    Toast.makeText(
                        context,
                        "Guardado satisfactoriamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigateUp()  // Navegar hacia atrás después de guardar
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Guardar")
            }
        }
    }
}
