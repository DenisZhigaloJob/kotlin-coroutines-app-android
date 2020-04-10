package by.dz.kotlincoroutines.models

data class Slot(val slot: Int, private val type: Type) {

    fun getName() = type.name
}