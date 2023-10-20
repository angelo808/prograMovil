package pe.edu.ulima.pm20232.aulavirtual.services

import pe.edu.ulima.pm20232.aulavirtual.models.Generation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenerationService {
    @GET("generation/list") // Reemplaza con la URL de tu punto final
    fun fetchAll(): Call<List<Generation>>
}