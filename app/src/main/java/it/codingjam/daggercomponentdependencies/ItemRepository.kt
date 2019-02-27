package it.codingjam.daggercomponentdependencies

import javax.inject.Inject
import javax.inject.Singleton

data class Item(
    val id: Int,
    val title: String
)

data class ItemDetail(
    val id: Int,
    val title: String,
    val description: String
)

@Singleton
class ItemRepository @Inject constructor() {
    fun list() = List(50) { Item(it, "item $it") }

    fun detail(id: Int) = ItemDetail(id, "item $id", "description $id")
}