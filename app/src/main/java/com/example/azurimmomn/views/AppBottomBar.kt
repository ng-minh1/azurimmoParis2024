package com.example.azurimmomn.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun AppBottomBar(navController: NavController) {

    val items = listOf(
        BottomNavItem("Actu", "actualites_list", Icons.Filled.Newspaper),
        BottomNavItem("Athlètes", "athletes_list", Icons.Filled.Person),
        BottomNavItem("Epreuves", "epreuves_list", Icons.Filled.Event),
        BottomNavItem("Pays", "pays_list", Icons.Filled.Flag),
        BottomNavItem("Sport", "sports_list", Icons.Filled.SportsHandball)
    )

    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: ImageVector)