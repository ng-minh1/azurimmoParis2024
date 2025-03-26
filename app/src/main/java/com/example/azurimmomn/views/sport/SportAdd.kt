package com.example.azurimmomn.views.sport

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.azurimmomn.model.Sport
import com.example.azurimmomn.viewsmodel.sport.SportViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField




@Composable
fun SportAdd(navController: NavController) {
    val viewModel: SportViewModel = viewModel()
    var nom by remember { mutableStateOf("") }
    var descriptif by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom du sport") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = descriptif,
            onValueChange = { descriptif = it },
            label = { Text("Descriptif") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nom.isNotBlank() && descriptif.isNotBlank()) {
                    val sport = Sport(id = 0, nom = nom, descriptif = descriptif)
                    viewModel.addSport(sport)
                    navController.navigate("sports_list")
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = nom.isNotBlank() && descriptif.isNotBlank()
        )
        {
            Text("Ajouter un Sport")
        }
    }
}