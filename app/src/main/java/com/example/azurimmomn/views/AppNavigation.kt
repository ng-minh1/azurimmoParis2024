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
import com.example.azurimmomn.views.joueur.JoueurList
import com.example.azurimmomn.views.sport.SportAdd
import com.example.azurimmomn.views.sport.SportEditForm
import android.os.Build
import androidx.annotation.RequiresApi


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
        composable("athletes_list") {
            AthleteList()
        }

        composable("athletes_list?sportId={sportId}") { backStackEntry ->
            val sportId = backStackEntry.arguments?.getString("sportId")?.toIntOrNull()
            AthleteList(sportId = sportId)
        }

        composable("epreuves_list") {
            EpreuveList()
        }
        composable("pays_list") {
            PaysList()
        }
        composable("sports_list") {
            SportList(navController = navController)
        }
        composable("add_sport") {
            SportAdd(navController = navController)
        }
        composable("joueurs_list") {
            JoueurList()
        }

        // Route pour modifier un sport
        composable("edit_sport/{sportId}") { backStackEntry ->
            val sportId = backStackEntry.arguments?.getString("sportId")?.toIntOrNull() ?: 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SportEditForm(sportId = sportId, navController = navController)
            } else {
                Text("Fonctionnalité non disponible sur cette version d'Android")
            }
        }
    }
}