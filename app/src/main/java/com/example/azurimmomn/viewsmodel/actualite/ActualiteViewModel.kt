package com.example.azurimmomn.viewsmodel.actualite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.model.Actualite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ActualiteViewModel : ViewModel() {

    // Liste mutable des bâtiments
    private val _actualites = MutableStateFlow<List<Actualite>>(emptyList())
    val actualites: StateFlow<List<Actualite>> = _actualites

    init {
        // Simuler un chargement de données initiales
        getActualites()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getActualites() {
        viewModelScope.launch {
            _actualites.value = listOf(
                Actualite(1, "Brian Champion de boxe", "il a tapé fort fort fort", LocalDate.of(2025,10,10)),
                Actualite(2, "Willy qui perd contre une chaise", "la chaise l'a humilié", LocalDate.of(2025,8,8)),

                )
        }
    }
}
