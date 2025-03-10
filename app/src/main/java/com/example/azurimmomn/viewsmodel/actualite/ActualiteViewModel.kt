package com.example.azurimmomn.viewsmodel.actualite

import androidx.lifecycle.ViewModel
import com.example.azurimmomn.model.Actualite
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActualiteViewModel : ViewModel() {

    private val _actualites = MutableStateFlow<List<Actualite>>(emptyList())
    val actualites: StateFlow<List<Actualite>> = _actualites

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getActualites()
    }


    private fun getActualites() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getActualites()
                _actualites.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement terminé")
            }
        }
    }


}