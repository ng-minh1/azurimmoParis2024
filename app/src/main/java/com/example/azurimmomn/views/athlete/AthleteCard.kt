package com.example.azurimmomn.views.athlete

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import com.example.azurimmomn.model.Athlete
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme


@Composable
fun AthleteCard(athlete: Athlete) {
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
            Text(text = athlete.nom, style = MaterialTheme.typography.bodyLarge)
            Text(text = athlete.prenom, style = MaterialTheme.typography.bodyMedium)
            Text(text = athlete.datenaiss, style = MaterialTheme.typography.bodyMedium)

        }
    }
}