package com.example.amitproject.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.amitproject.Screen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainScreen(modifier: Modifier,navController: NavController ? = null, viewModel: MainScreenViewModel = hiltViewModel()){

    val meals by viewModel.meals.collectAsState()

    Box(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 30.dp) ){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(meals.categories){
                MealItem(title = it.strCategory ?: "", imageUrl = it.strCategoryThumb ?: ""){
                    val gson = Gson()
                    val categoryDataJson = gson.toJson(it)
                    // Encode the JSON to make it URL-safe
                    val encodedCategoryData = URLEncoder.encode(categoryDataJson, StandardCharsets.UTF_8.toString())
                    navController?.navigate("${Screen.MealDetailsScreen.route}/$encodedCategoryData")

                }
            }
        }
    }

}