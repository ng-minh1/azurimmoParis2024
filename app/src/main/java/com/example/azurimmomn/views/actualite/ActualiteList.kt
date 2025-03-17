package com.example.azurimmomn.views.actualite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azurimmomn.viewsmodel.actualite.ActualiteViewModel



@Composable
fun ActualiteList(viewModel: ActualiteViewModel = viewModel()) {
    // Observer les données de manière réactive
    val actualites by viewModel.actualites.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage!!, color = Color.Red)
        else -> LazyColumn {
            items(actualites) { actualite ->
                ActualiteCard(actualite = actualite)
            }
        }
    }
}