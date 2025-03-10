package com.example.azurimmomn.viewsmodel.pays

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.model.Pays
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaysViewModel : ViewModel() {

    // Liste mutable des bâtiments
    private val _payss = MutableStateFlow<List<Pays>>(emptyList())
    val payss: StateFlow<List<Pays>> = _payss

    init {
        // Simuler un chargement de données initiales
        getPays()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getPays() {
        viewModelScope.launch {
            _payss.value = listOf(
                Pays(1,  "Vietnam"),
                Pays(2,  "France"),
            )
        }
    }
}
