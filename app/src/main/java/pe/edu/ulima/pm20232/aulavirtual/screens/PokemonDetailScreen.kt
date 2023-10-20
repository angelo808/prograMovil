package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.PokemonDetailScreenViewModel

@Composable
fun PokemonDetailScreen(navController: NavController, model: PokemonDetailScreenViewModel) {
    Text("pokemon id : ${model.pokemonId}")
}