package com.example.azurimmomn.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import com.example.azurimmomn.views.actualite.ActualiteList
import com.example.azurimmomn.views.athlete.AthleteList
import com.example.azurimmomn.views.epreuve.EpreuveList
import com.example.azurimmomn.views.pays.PaysList
import com.example.azurimmomn.views.sport.SportList

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "actualites_list",
        modifier = modifier
    ) {
        composable("actualites_list") {
            ActualiteList()
        }
        composable("batiments_list") {
            AthleteList()
        }
        composable("epreuves_list") {
            EpreuveList()
        }
        composable("pays_list") {
            PaysList()
        }
        composable("sports_list") {
            SportList()
        }
    }
}