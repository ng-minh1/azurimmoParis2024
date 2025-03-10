package com.example.azurimmomn.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import com.example.azurimmomn.views.actualite.ActualiteList
import com.example.azurimmomn.views.athlete.AthleteList

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "batiments_list",
        modifier = modifier
    ) {
        composable("actualites_list") {
            ActualiteList()
        }
        composable("batiments_list") {
            AthleteList()
        }
        composable("epreuves_list") {
            Text("Page Epreuves")
        }
        composable("pays_list") {
            Text("Page Pays")
        }
        composable("sports_list") {
            Text("Page Sports")
        }
    }
}