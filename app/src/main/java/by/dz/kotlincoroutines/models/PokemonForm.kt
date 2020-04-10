package by.dz.kotlincoroutines.models

import com.google.gson.annotations.SerializedName

data class PokemonForm(
    val id: Long,
    @SerializedName("version_group")
    private val versionGroup: VersionGroup
) {
    fun getVersionGroupName() = versionGroup.name
}