package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PokemonDetailScreenViewModel: ViewModel() {
    var pokemonId: Int by mutableStateOf(0)
}