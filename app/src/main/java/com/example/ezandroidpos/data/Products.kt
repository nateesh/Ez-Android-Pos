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
    val name: String,
    val price: Double,
    val category: Category = Category.Drinks,
) {
    constructor() : this("", 0.0, getCategory("")
    )
}

val longBlack: Item = Item(name = "Long Black", price = 4.5, category = Category.Drinks)
val latte: Item = Item(name = "Latte", price = 4.5, category = Category.Drinks)
val hotChocolate: Item = Item(name = "Hot Chocolate", price = 4.5, category = Category.Drinks)
val tea: Item = Item(name = "Tea", price = 4.5, category = Category.Drinks)

val avoToast: Item = Item(name = "Avo + Toast", price = 14.5, category = Category.Food)
val eggsToast: Item = Item(name = "Eggs + Toast", price = 12.5, category = Category.Food)
val baconEgg: Item = Item(name = "Bacon Egg Roll", price = 12.0, category = Category.Food)
val pancakes: Item = Item(name = "Pancakes", price = 12.0, category = Category.Food)
val baconPancakes: Item = Item(name = "Bacon Pancakes", price = 14.0, category = Category.Food)

val chocMuffin: Item = Item(name = "Chocolate Muffin", price = 5.5, category = Category.Sweets)
val chocCake: Item = Item(name = "Chocolate Cake", price = 5.0, category = Category.Sweets)
val brownie: Item = Item(name = "Brownie", price = 4.0, category = Category.Sweets)
val chocSlice: Item = Item(name = "Chocolate Slice", price = 4.0, category = Category.Sweets)

val beans1kg: Item = Item(name = "1kg Beans", price = 40.0, category = Category.Retail)
val beans500g: Item = Item(name = "500g Beans", price = 23.0, category = Category.Retail)
val beans250g: Item = Item(name = "250g Beans", price = 12.5, category = Category.Retail)
val aeroPress: Item = Item(name = "AeroPress", price = 20.0, category = Category.Retail)
val chemex: Item = Item(name = "Chemex", price = 50.0, category = Category.Retail)
val frenchPress: Item = Item(name = "French Press", price = 30.0, category = Category.Retail)