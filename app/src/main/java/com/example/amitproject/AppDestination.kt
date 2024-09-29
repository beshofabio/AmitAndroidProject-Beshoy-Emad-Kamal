package com.example.amitproject

sealed class Screen(val route : String){
    object MainScreen : Screen("mainScreen")
    object MealDetailsScreen : Screen("mealDetailsScreen")
}