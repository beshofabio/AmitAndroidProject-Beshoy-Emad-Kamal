package com.example.amitproject.ui.screens.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.domain.entity.mainscreen.MealModelItems
import com.example.domain.usecase.mainscreen.GetMealsFromRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "MainScreenViewModel"

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getMealsFromRemote : GetMealsFromRemote
) : ViewModel() {
    private val _meals = MutableStateFlow<MealModelItems>(MealModelItems(emptyList()))
    val meals = _meals.asStateFlow()

    init {
        getMeals()
    }

    private fun getMeals(){
        viewModelScope.launch {
            try {
                _meals.value = getMealsFromRemote()
                Log.d(TAG, "getMeals: ${Thread.currentThread().name}")
            }catch (e : Exception){
                if(e is HttpException){
                    Log.d(TAG,"Http Exception: ${e.message}")
                }
                else {
                    Log.d(TAG,"getMeals: ${e.message}")
                }
            }
        }
    }
}

