package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.edu.ulima.pm20232.aulavirtual.configs.BackendClient
import pe.edu.ulima.pm20232.aulavirtual.configs.SingleLiveEvent
import pe.edu.ulima.pm20232.aulavirtual.models.Generation
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.GenerationService
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService
import retrofit2.Response

class PokemonScreenViewModel: ViewModel() {
    private val pokemonService = BackendClient.buildService(PokemonService::class.java)
    private val generationService = BackendClient.buildService(GenerationService::class.java)
    private val coroutine: CoroutineScope = viewModelScope

    private var _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> get() = _pokemons
    fun setPokemons(newItems: List<Pokemon>) {
        _pokemons.value = newItems
    }

    val generationsMap = mutableMapOf<Int, String>()

    fun fetchAll(){

    }

    fun fetchGenerations(){
        coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = generationService.fetchAll().execute()
                    if (response.isSuccessful) {
                        val list: List<Generation> = response.body()!!
                        for(g: Generation in list){
                            val generationId = g.id
                            val generationName = g.name
                            generationsMap[generationId] = generationName
                        }
                    } else {
                        // Maneja errores
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }
}