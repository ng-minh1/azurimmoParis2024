package com.example.azurimmomn.viewsmodel.sport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportViewModel : ViewModel() {

    // Liste mutable des bâtiments
    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    init {
        // Simuler un chargement de données initiales
        getSports()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getSports() {
        viewModelScope.launch {
            _sports.value = listOf(
                Sport(1,  "boxe","faut se battre"),
                Sport(2,  "lancer de poids","faut lancer très fort"),
            )
        }
    }
}
