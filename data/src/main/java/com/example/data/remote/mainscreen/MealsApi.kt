package com.example.data.remote.mainscreen

import com.example.domain.entity.mainscreen.MealModelItems
import retrofit2.http.GET

interface MealsApi {

    @GET("categories.php")
    suspend fun getMeals(): MealModelItems
    
}