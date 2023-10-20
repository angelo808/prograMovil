package pe.edu.ulima.pm20232.aulavirtual.services

import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
class PokemonService {
    var pokemonList: ArrayList<Pokemon> = ArrayList<Pokemon>()

    constructor(){
        pokemonList.add(Pokemon(id = 1, name = "BULBASAUR", number = 1,weight = 6.9, height = 0.7, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/1.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 2, name = "IVYSAUR", number = 2,weight = 13.0, height = 1.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/2.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 3, name = "VENUSAUR", number = 3,weight = 100.0, height = 2.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 4, name = "CHARMANDER", number = 4,weight = 8.5, height = 0.6, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/4.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 5, name = "CHARMELEON", number = 5,weight = 19.0, height = 1.1, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/5.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 6, name = "CHARIZARD", number = 6,weight = 90.5, height = 1.7, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/6.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 7, name = "SQUIRTLE", number = 7,weight = 9.0, height = 0.5, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/7.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 8, name = "WARTORTLE", number = 8,weight = 22.5, height = 1.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/8.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 9, name = "BLASTOISE", number = 9,weight = 85.5, height = 1.6, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/9.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 10, name = "CATERPIE", number = 10,weight = 2.9, height = 0.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/10.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 11, name = "METAPOD", number = 11,weight = 9.9, height = 0.7, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/11.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 12, name = "BUTTERFREE", number = 12,weight = 32.0, height = 1.1, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/12.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 13, name = "WEEDLE", number = 13,weight = 3.2, height = 0.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/13.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 14, name = "KAKUNA", number = 14,weight = 10.0, height = 0.6, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/14.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 15, name = "BEEDRILL", number = 15,weight = 29.5, height = 1.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/15.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 16, name = "PIDGEY", number = 16,weight = 1.8, height = 0.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/16.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 17, name = "PIDGEOTTO", number = 17,weight = 30.0, height = 1.1, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/17.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 18, name = "PIDGEOT", number = 18,weight = 39.5, height = 1.5, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/18.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 19, name = "RATTATA", number = 19,weight = 3.5, height = 0.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/19.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 20, name = "RATICATE", number = 20,weight = 18.5, height = 0.7, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/20.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 21, name = "SPEAROW", number = 21,weight = 2.0, height = 0.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/21.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 22, name = "FEAROW", number = 22,weight = 30.8, height = 1.2, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/22.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 23, name = "EKANS", number = 23,weight = 6.9, height = 2.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/23.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 24, name = "ARBOK", number = 24,weight = 65.0, height = 3.5, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/24.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 25, name = "PIKACHU", number = 25,weight = 6.0, height = 0.4, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/25.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 26, name = "RAICHU", number = 26,weight = 30.0, height = 0.8, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/26.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 27, name = "SANDSHREW", number = 27,weight = 12.0, height = 0.6, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/27.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 28, name = "SANDSLASH", number = 28,weight = 25.9, height = 1.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/28.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 29, name = "NIDORAN HEMBRA", number = 29,weight = 7.0, height = 0.4, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/29.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 30, name = "NIDORINA", number = 30,weight = 20.0, height = 0.8, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/30.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 31, name = "NIDOQUEEN", number = 31,weight = 60.0, height = 1.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/31.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 32, name = "NIDORAN MACHO", number = 32,weight = 9.0, height = 0.5, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/32.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 33, name = "NIDORINO", number = 33,weight = 19.5, height = 0.9, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/33.png", generationId = 1,generationName = "PRIMERA"))
        pokemonList.add(Pokemon(id = 34, name = "NIDOKING", number = 34,weight = 62.0, height = 1.4, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/34.png", generationId = 2,generationName = "SEGUNDA"))
        pokemonList.add(Pokemon(id = 35, name = "CLEFAIRY", number = 35,weight = 7.5, height = 0.6, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/35.png", generationId = 2,generationName = "SEGUNDA"))
        pokemonList.add(Pokemon(id = 36, name = "CLEFABLE", number = 36,weight = 40.0, height = 1.3, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/36.png", generationId = 2,generationName = "SEGUNDA"))
        pokemonList.add(Pokemon(id = 37, name = "VULPIX", number = 37,weight = 9.9, height = 0.6, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/37.png", generationId = 3,generationName = "TERCERA"))
        pokemonList.add(Pokemon(id = 38, name = "NINETALES", number = 38,weight = 19.9, height = 1.1, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/38.png", generationId = 3,generationName = "TERCERA"))
        pokemonList.add(Pokemon(id = 39, name = "JIGGLYPUFF", number = 39,weight = 5.5, height = 0.5, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/39.png", generationId = 3,generationName = "TERCERA"))
        pokemonList.add(Pokemon(id = 40, name = "WIGGLYTUFF", number = 40,weight = 12.0, height = 1.0, imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/40.png", generationId = 3,generationName = "TERCERA"))
    }

    fun listAll(): List<Pokemon> {
        return pokemonList
    }

    fun pokemonListByGenerationId(generationId: Int): List<Pokemon>  {
        var reducedList = ArrayList<Pokemon>()
        for (pokemon in pokemonList) {
            if(pokemon.generationId == generationId){
                reducedList.add(pokemon)
            }
        }
        return reducedList
    }
}
 */
interface PokemonService {
    @GET("pokemon/list") // Reemplaza con la URL de tu punto final
    fun fetchAll(
        @Query("generation_id") generationId: Int? = null,
    ): Call<List<Pokemon>>
}