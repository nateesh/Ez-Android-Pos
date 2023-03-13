package com.example.ezandroidpos.data

enum class Category {
    Drinks,
    Food,
    Sweets,
    Retail,
}
fun getCategory(category: String): Category {
    return when (category) {
        "Drinks" -> Category.Drinks
        "Food" -> Category.Food
        "Sweets" -> Category.Sweets
        "Retail" -> Category.Retail
        else -> Category.Retail
    }
}
data class Item(
    val refId: Int,
    val name: String,
    val price: Double,
    val category: Category = Category.Drinks,
) {
    constructor() : this(0,"", 0.0, getCategory("")
    )
}