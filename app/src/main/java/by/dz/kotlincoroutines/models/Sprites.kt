package by.dz.kotlincoroutines.models

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("back_default")
    private val backDefault: String?,
    @SerializedName("back_female")
    private val backFemale: String?,
    @SerializedName("back_shiny")
    private val backShiny: String?,
    @SerializedName("back_shiny_female")
    private val backShinyFemale: String?,
    @SerializedName("front_default")
    private val frontDefault: String?,
    @SerializedName("front_female")
    private val frontFemale: String?,
    @SerializedName("front_shiny")
    private val frontShiny: String?,
    @SerializedName("front_shiny_female")
    private val frontShinyFemale: String?
) {
    fun getSprites(): List<String> {
        val list = mutableListOf<String>()
        backDefault?.let { list.add(it) }
        backFemale?.let { list.add(it) }
        backShiny?.let { list.add(it) }
        backShinyFemale?.let { list.add(it) }
        frontDefault?.let { list.add(it) }
        frontFemale?.let { list.add(it) }
        frontShiny?.let { list.add(it) }
        frontShinyFemale?.let { list.add(it) }
        return list
    }

    fun getImage() = frontDefault ?: ""
}