package com.example.teammanagement.UIscreens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = "MainScreen") {


        composable("MainScreen"){
            MainScreen(navController)
        }
        composable("profile"){
            ProfileScreen(navController)
        }
//        composable("todo/{empId}") { backStackEntry ->
//            val empId = backStackEntry.arguments?.getString("empId")
//            if (empId != null) {
//                TodoTasks(empId,navController)
//            }
//        }
        composable("todos/{empId}") { backStackEntry ->
            val empId = backStackEntry.arguments?.getString("empId")
            if (empId != null) {
                TodosTasks(empId,navController)
            }
        }
    }
}


