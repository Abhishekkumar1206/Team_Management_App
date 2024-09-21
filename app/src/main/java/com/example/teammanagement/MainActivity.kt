package com.example.teammanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.teammanagement.DB.AppDatabase
import com.example.teammanagement.UIscreens.MainScreen
import com.example.teammanagement.UIscreens.NavGraph
import com.example.teammanagement.ui.theme.TeamManagementTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    companion object{
        lateinit var appDB:AppDatabase
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         appDB=synchronized(this){
             Room.databaseBuilder(
                applicationContext,
                 AppDatabase::class.java,
                "team_db"
            ).fallbackToDestructiveMigration(true).build()
        }
        enableEdgeToEdge()

        setContent {
            TeamManagementTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }

}
