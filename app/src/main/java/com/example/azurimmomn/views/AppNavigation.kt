package com.example.azurimmomn.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "batiments_list",
        modifier = modifier
    ) {
        composable("batiments_list") {
            Text("Page batiments")
        }
        composable("appartements_list") {
            Text("Page appartements")
        }
        composable("contrats_list") {
            Text("Page contrats")
        }
        composable("locataires_list") {
            Text("Page locataires")
        }
        composable("paiements_list") {
            Text("Page paiements")
        }
    }
}