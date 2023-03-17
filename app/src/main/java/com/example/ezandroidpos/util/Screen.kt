package com.example.ezandroidpos.util

sealed class Screen(val route: String) {
    object PointOfSaleScreen : Screen("main_screen")
    object ProductAddScreen : Screen("order_screen")
}
