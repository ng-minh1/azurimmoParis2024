package com.example.azurimmomn.viewsmodel.sport

import androidx.lifecycle.ViewModel
import com.example.azurimmomn.model.Sport
import androidx.lifecycle.viewModelScope
import com.example.azurimmomn.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportViewModel : ViewModel() {

    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _sport = MutableStateFlow<Sport?>(null)
    val sport: StateFlow<Sport?> = _sport

    // Ajouter un état pour suivre le succès d'une opération
    private val _operationSuccess = MutableStateFlow(false)
    val operationSuccess: StateFlow<Boolean> = _operationSuccess

    init {
        getSports()
    }


    private fun getSports() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getSports()
                _sports.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement terminé")
            }
        }
    }

    fun getSport(sportId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getSport(sportId)
                _sport.value = response.body()
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement du sport terminé")
            }
        }
    }

    fun addSport(sport: Sport) {
        viewModelScope.launch {
            _isLoading.value = true
            _operationSuccess.value = false
            try {
                // Envoi à l'API (ici, un POST)
                val response = RetrofitInstance.api.addSport(sport)
                if (response.isSuccessful) {
                    // Ajout réussi, on met à jour la liste des sports
                    getSports() // Recharge les sports pour inclure le nouveau
                    _operationSuccess.value = true
                } else {
                    _errorMessage.value = "Erreur lors de l'ajout du sport : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Cette fonction peut être utilisée si vous souhaitez avoir une API distincte pour la mise à jour
    // Si votre API utilise la même fonction pour ajouter et mettre à jour, vous pouvez continuer à utiliser addSport
    fun updateSport(sport: Sport) {
        viewModelScope.launch {
            _isLoading.value = true
            _operationSuccess.value = false
            try {
                // Dans cet exemple, nous réutilisons l'endpoint addSport
                // mais vous pourriez avoir un endpoint distinct comme:
                // val response = RetrofitInstance.api.updateSport(sport.id, sport)
                val response = RetrofitInstance.api.addSport(sport)

                if (response.isSuccessful) {
                    getSports() // Recharge les sports pour mettre à jour la liste
                    _operationSuccess.value = true
                } else {
                    _errorMessage.value = "Erreur lors de la modification du sport : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Réinitialiser les valeurs
    fun resetOperationStatus() {
        _operationSuccess.value = false
        _errorMessage.value = null
    }
}