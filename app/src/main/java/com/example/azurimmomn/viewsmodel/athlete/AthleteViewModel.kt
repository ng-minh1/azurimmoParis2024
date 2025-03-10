package com.example.azurimmomn.viewsmodel.athlete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.model.Athlete
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AthleteViewModel : ViewModel() {

    // Liste mutable des bâtiments
    private val _athletes = MutableStateFlow<List<Athlete>>(emptyList())
    val athletes: StateFlow<List<Athlete>> = _athletes

    init {
        // Simuler un chargement de données initiales
        getAthletes()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getAthletes() {
        viewModelScope.launch {
            _athletes.value = listOf(
                Athlete(1,  "Dupont", "Jean"),
                Athlete(2,  "Smith", "John"),
                Athlete(3,  "Tanaka", "Yuki")
            )
        }
    }
}
