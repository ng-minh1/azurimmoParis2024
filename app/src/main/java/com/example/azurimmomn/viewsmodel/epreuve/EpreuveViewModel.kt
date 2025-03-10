package com.example.azurimmomn.viewsmodel.epreuve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.model.Epreuve
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpreuveViewModel : ViewModel() {

    // Liste mutable des bâtiments
    private val _epreuves = MutableStateFlow<List<Epreuve>>(emptyList())
    val epreuves: StateFlow<List<Epreuve>> = _epreuves

    init {
        // Simuler un chargement de données initiales
        getEpreuves()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getEpreuves() {
        viewModelScope.launch {
            _epreuves.value = listOf(
                Epreuve(1,  "200m"),
                Epreuve(2,  "500m"),
            )
        }
    }
}
