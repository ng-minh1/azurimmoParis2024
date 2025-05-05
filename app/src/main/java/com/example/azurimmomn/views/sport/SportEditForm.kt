package com.example.azurimmomn.views.sport

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.azurimmomn.model.Sport
import com.example.azurimmomn.viewsmodel.sport.SportViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SportEditForm(
    sportId: Int,
    navController: NavController,
    viewModel: SportViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val sport by viewModel.sport.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val operationSuccess by viewModel.operationSuccess.collectAsState()

    var nom by remember { mutableStateOf("") }
    var descriptif by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    // Charger les données du sport à modifier
    LaunchedEffect(sportId) {
        viewModel.getSport(sportId)
    }

    // Mettre à jour les champs une fois les données chargées
    LaunchedEffect(sport) {
        sport?.let {
            nom = it.nom
            descriptif = it.descriptif
        }
    }

    // Observer le succès de l'opération pour retourner à la liste des sports
    LaunchedEffect(operationSuccess) {
        if (operationSuccess) {
            viewModel.resetOperationStatus()
            navController.popBackStack()  // Retour à l'écran précédent après la modification
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (showErrorDialog && errorMessage != null) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Erreur") },
            text = { Text(errorMessage ?: "Une erreur est survenue") },
            confirmButton = {
                TextButton(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Modifier un sport",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom du sport") },
            modifier = Modifier.fillMaxWidth(),
            isError = nom.isBlank()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descriptif,
            onValueChange = { descriptif = it },
            label = { Text("Descriptif") },
            modifier = Modifier.fillMaxWidth(),
            isError = descriptif.isBlank()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Annuler")
            }

            Button(
                onClick = {
                    if (nom.isNotBlank() && descriptif.isNotBlank()) {
                        val updatedSport = Sport(
                            id = sportId,
                            nom = nom,
                            descriptif = descriptif
                        )

                        coroutineScope.launch {
                            try {
                                // Utilisez addSport comme dans votre code original
                                // Vous pourriez remplacer par updateSport si vous ajoutez cette fonction
                                viewModel.addSport(updatedSport)
                            } catch (e: Exception) {
                                showErrorDialog = true
                            }
                        }
                    } else {
                        showErrorDialog = true
                    }
                },
                enabled = nom.isNotBlank() && descriptif.isNotBlank() && !isLoading
            ) {
                Text("Enregistrer")
            }
        }
    }
}