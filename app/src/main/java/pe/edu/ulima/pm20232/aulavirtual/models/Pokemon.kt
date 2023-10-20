package pe.edu.ulima.pm20232.aulavirtual.models

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val id: Int,
    val name: String,
    val number: Long,
    val weight: Double,
    val height: Double,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("generation_id")
    val generationId: Int,
    val generationName: String
)