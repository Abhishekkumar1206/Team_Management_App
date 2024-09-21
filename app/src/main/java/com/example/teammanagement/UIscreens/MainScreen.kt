package com.example.teammanagement.UIscreens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.teammanagement.NavItem


@Composable
fun MainScreen(navController: NavController){

    val navItemList= listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Members", Icons.Default.Person)
    )
    var selectedIndex by remember{
        mutableIntStateOf(0)
    }
    Scaffold(modifier=Modifier.fillMaxSize(),
        bottomBar =
        {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected =selectedIndex==index,
                        onClick = { selectedIndex=index },
                        icon = {
                            Icon(imageVector=navItem.icon,contentDescription="Icon")
                        },
                        label = {
                                Text(text = navItem.label)
                        }
                    )
                }
        }
        }) {
        innerpadding->
        ContentScreen(modifier=Modifier.padding(innerpadding),selectedIndex,navController)
    }
}

@Composable
fun ContentScreen(modifier: Modifier=Modifier,selectedIndex:Int,navController:NavController) {


    when(selectedIndex){
        0-> HomeScreen(navController)
        1-> ProfileScreen(navController)
    }
}


