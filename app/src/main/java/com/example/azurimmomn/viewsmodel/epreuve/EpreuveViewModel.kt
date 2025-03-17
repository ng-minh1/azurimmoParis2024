package com.example.azurimmomn.viewsmodel.epreuve

import androidx.lifecycle.ViewModel
import com.example.azurimmomn.model.Epreuve
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpreuveViewModel : ViewModel() {

    private val _epreuves = MutableStateFlow<List<Epreuve>>(emptyList())
    val epreuves: StateFlow<List<Epreuve>> = _epreuves

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getEpreuves()
    }


    private fun getEpreuves() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getEpreuves()
                _epreuves.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement terminé")
            }
        }
    }


}