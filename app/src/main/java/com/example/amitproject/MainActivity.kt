package com.example.amitproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.amitproject.ui.screens.mainscreen.MainScreen
import com.example.amitproject.ui.screens.mealdetails.MealsDetailsScreen
import com.example.amitproject.ui.theme.AmitProjectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmitProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MealsAppCompose(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}

@Composable
fun MealsAppCompose(modifier: Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable(Screen.MainScreen.route) { MainScreen(modifier,navController) }
        composable(
            "${Screen.MealDetailsScreen.route}/{Category}",
            arguments = listOf(navArgument("Category") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            MealsDetailsScreen(modifier = modifier)
        }
    }
}

