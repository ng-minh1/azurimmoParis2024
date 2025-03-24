package com.example.azurimmomn.model

import java.time.LocalDate


class Joueur (
    val id: Int,
    val nom: String,
    val prenom: String,
    val datenaiss: LocalDate,
    val sport: Sport,
    )