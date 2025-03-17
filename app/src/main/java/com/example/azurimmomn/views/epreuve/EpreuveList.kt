package com.example.azurimmomn.views.epreuve

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azurimmomn.views.epreuve.EpreuveCard
import com.example.azurimmomn.viewsmodel.epreuve.EpreuveViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color



@Composable
fun EpreuveList(viewModel: EpreuveViewModel = viewModel()) {
    // Observer les données de manière réactive
    val epreuves by viewModel.epreuves.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage!!, color = Color.Red)
        else -> LazyColumn {
            items(epreuves) { epreuve ->
                EpreuveCard(epreuve = epreuve)
            }
        }
    }
}




