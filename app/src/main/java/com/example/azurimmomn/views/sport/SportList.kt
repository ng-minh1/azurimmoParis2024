package com.example.azurimmomn.views.sport

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azurimmomn.viewsmodel.sport.SportViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
fun SportList(viewModel: SportViewModel = viewModel(), navController: NavController) {
    // Observer les données de manière réactive
    val sports by viewModel.sports.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage!!, color = Color.Red)
        else -> LazyColumn {
            items(sports) { sport ->
                SportCard(sport = sport, navController = navController)
            }
        }
    }
}