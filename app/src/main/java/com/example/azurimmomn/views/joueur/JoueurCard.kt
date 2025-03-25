package com.example.azurimmomn.views.joueur

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import com.example.azurimmomn.model.Joueur
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import java.time.format.DateTimeFormatter


@Composable
fun JoueurCard(joueur: Joueur) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            Text(text = " ${joueur.nom} ${joueur.prenom}", style = MaterialTheme.typography.bodyLarge)

            Text(text = "Date de naissance : ${joueur.datenaiss.format(formatter)} ", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Sport favori : ${joueur.sport.nom}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Niveau : ${joueur.niveau.libelle}", style = MaterialTheme.typography.bodyMedium)




        }
    }
}