package pe.edu.ulima.pm20232.aulavirtual.configs

import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}