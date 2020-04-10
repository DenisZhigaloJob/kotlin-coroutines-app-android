package by.dz.kotlincoroutines.models

data class Pokemon(
    val id: Long,
    val name: String,
    val order: Int,
    val weight: Int,
    val height: Int,
    private val types: List<Slot>,
    private val sprites: Sprites
) {
    fun getTypes() = types.map { it.getName() }
    fun getSprites() = sprites.getSprites()
    fun getImage() = sprites.getImage()
}