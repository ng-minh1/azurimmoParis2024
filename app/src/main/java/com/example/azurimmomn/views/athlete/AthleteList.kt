package com.example.azurimmomn.views.athlete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.azurimmomn.viewsmodel.athlete.AthleteViewModel
import com.example.azurimmomn.viewsmodel.sport.SportViewModel


@Composable
fun AthleteList(
    sportId: Int? = null,
    athleteViewModel: AthleteViewModel = viewModel(),
    sportViewModel: SportViewModel = viewModel() ) {

    val athletes by athleteViewModel.athletes.collectAsState()
    val isLoading by athleteViewModel.isLoading.collectAsState()
    val errorMessage by athleteViewModel.errorMessage.collectAsState()
    val sport by sportViewModel.sport.collectAsState()

    LaunchedEffect(sportId) {
        if (sportId == null) {
            athleteViewModel.getAthletes()  // Charge tous les appartements
        } else {
            athleteViewModel.getAthletesBySport(sportId)
            sportViewModel.getSport(sportId)// Charge par bâtiment
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erreur inconnue",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {


                LazyColumn {

                    // BLOC D'INFOS SUR LE BATIMENT SI SELECTIONNE AVANT
                    if (sport != null) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally // Centrer le contenu horizontalement
                            ) {
                                Text(
                                    text = "Informations sur le sport",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Adresse : ${sport?.nom ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Ville : ${sport?.descriptif ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    if (athletes.isNotEmpty()) {


                        // Titre Liste des appartements
                        item {
                            Text(
                                text = "Liste des athletes",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center, // Alignement à gauche
                                color = MaterialTheme.colorScheme.primary
                            )
                        }


                        // Liste des appartements
                        items(athletes) { athlete ->
                            AthleteCard(athlete = athlete)
                        }
                    }

                    else {
                        // Il n'y a pas d'appartement pour ce batiment
                        item {
                            Text(
                                text = "Pas d'athlete pour ce sport",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center, // Alignement à gauche
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }

}
