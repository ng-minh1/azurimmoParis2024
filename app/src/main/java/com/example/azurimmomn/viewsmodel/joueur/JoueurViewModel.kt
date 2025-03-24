package com.example.azurimmomn.viewsmodel.joueur

import androidx.lifecycle.ViewModel
import com.example.azurimmomn.model.Joueur
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JoueurViewModel : ViewModel() {

    private val _joueurs = MutableStateFlow<List<Joueur>>(emptyList())
    val joueurs: StateFlow<List<Joueur>> = _joueurs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getJoueurs()
    }


    private fun getJoueurs() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getJoueurs()
                _joueurs.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement terminé")
            }
        }
    }


}