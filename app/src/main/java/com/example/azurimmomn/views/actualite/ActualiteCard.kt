package com.example.azurimmomn.views.actualite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import com.example.azurimmomn.model.Actualite
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import java.time.format.DateTimeFormatter


@Composable
fun ActualiteCard(actualite: Actualite) {
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

            Text(text = actualite.titre, style = MaterialTheme.typography.bodyLarge)
            Text(text = actualite.contenu, style = MaterialTheme.typography.bodyMedium)
            Text(text = actualite.date.format(formatter), style = MaterialTheme.typography.bodyMedium)

        }
    }
}