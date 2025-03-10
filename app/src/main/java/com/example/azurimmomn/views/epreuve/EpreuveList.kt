package com.example.azurimmomn.views.epreuve

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azurimmomn.viewsmodel.epreuve.EpreuveViewModel



@Composable
fun EpreuveList(viewModel: EpreuveViewModel = viewModel()) {
    // Observer les données de manière réactive
    val epreuves by viewModel.epreuves.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(epreuves) { epreuve ->
            EpreuveCard(epreuve = epreuve)
        }
    }
}