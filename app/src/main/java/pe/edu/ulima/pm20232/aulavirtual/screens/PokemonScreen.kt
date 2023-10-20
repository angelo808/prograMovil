package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.HomeScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.PokemonScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray1200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonsGrid(navController: NavController, model: PokemonScreenViewModel){
    var intValue by remember { mutableStateOf(0) }
    val pokemons by model.pokemons.collectAsState()
    LazyVerticalGrid(
        cells = GridCells.Fixed(4) // Specify the number of columns
    ) {
        items(pokemons.size) { i ->
            Column(){
                println(pokemons[i].imageUrl)
                Image(
                    painter = rememberImagePainter(data = pokemons[i].imageUrl),
                    contentDescription = pokemons[i].name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            //Log.d("POKEMONS", model.pokemons[i].id.toString())
                            intValue = pokemons[i].id.toInt()
                            navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                        },
                )
                Text(pokemons[i].name)
            }
        }
    }
}

@Composable
fun SelectGenerations(model: PokemonScreenViewModel) {
    var expanded by remember { mutableStateOf(false) }
    // val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        Modifier.padding(bottom = 20.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Generaciones de Pokmones")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledLabelColor = Gray1200, // Change the label color when disabled
                disabledBorderColor = Gray1200, // Change the border color when disabled
                disabledTextColor = Gray1200
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            for ((key, value) in model.generationsMap) {
                DropdownMenuItem(onClick = {
                    // model.fetchAll()
                    selectedText = value
                    expanded = false
                }) {
                    Text(text = value)
                }
            }
        }
    }
}

@Composable
fun PokemonScreen(navController: NavController) {
    val model: PokemonScreenViewModel = PokemonScreenViewModel()
    model.fetchAll()
    model.fetchGenerations()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        SelectGenerations(model)
        PokemonsGrid(navController, model)
    }
}