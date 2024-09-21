package com.example.teammanagement.UIscreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teammanagement.Controller.EmployeeViewModel
import com.example.teammanagement.Controller.TodosViewModel
import com.example.teammanagement.Entities.Employees
import kotlinx.coroutines.delay
import java.time.LocalDate



@SuppressLint("Range")
@Composable
fun HomeScreen(navController:NavController) {

    val employeeViewModel: EmployeeViewModel = viewModel()
    val Emplist by employeeViewModel.EmpList.observeAsState()
    val upcominglist by employeeViewModel.todosExceedingFiveDays.observeAsState()
    val scrollState = remember { ScrollState(0) }
    val context= LocalContext.current
    var isLoading by remember {
        mutableIntStateOf(0)
    }
    var inputtext by remember {
        mutableStateOf("")
    }
    var inputtext1 by remember {
        mutableStateOf("")
    }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = { isLoading = 1 }, modifier = Modifier
                .padding(90.dp)
                .fillMaxWidth(1f)) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {

         if(isLoading==0) {

         Column {


             Card(
                 modifier = Modifier
                     .padding(10.dp)
                     .height(100.dp)
                     .verticalScroll(scrollState),
                 shape = RoundedCornerShape(8.dp),
                 colors = CardDefaults.cardColors(containerColor = Color.White), // Use CardDefaults for containerColor
                 elevation = CardDefaults.cardElevation(8.dp) /// Use shadowElevation instead of elevation
             ) {
                 Box(
                     modifier = Modifier
                         .fillMaxSize()
                         .padding(16.dp), contentAlignment = Alignment.Center
                 ) {

                     Column(verticalArrangement = Arrangement.spacedBy(16.dp),
                         horizontalAlignment = Alignment.CenterHorizontally,
                          ) {
                         Text(
                             text = "Total Number of Employees",
                             fontSize = 20.sp,
                             style = TextStyle(fontWeight = FontWeight.Normal)
                         )

                         Text(text = Emplist?.size.toString(), fontSize = 20.sp)
                     }

                 }

             }
             Column(horizontalAlignment = Alignment.CenterHorizontally,  // Center content horizontally
                 verticalArrangement = Arrangement.Center,
                 modifier=Modifier.padding(40.dp)){
                    Text(text = "Upcoming Deadline Tasks"
                      ,style= TextStyle(fontWeight= FontWeight.Medium,
                          fontSize = 25.sp)
                    )
             }
             Card(
                 modifier = Modifier
                     .padding(30.dp)
                     .fillMaxWidth()
                     .height(400.dp),
                 shape = RoundedCornerShape(8.dp),
                 colors = CardDefaults.cardColors(containerColor = Color.White),
                 elevation = CardDefaults.cardElevation(8.dp)
             ){

                 Column(modifier=Modifier.verticalScroll(scrollState)){

                 upcominglist?.forEach { task ->
                     Card(
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(30.dp)
                             .height(40.dp),
                         shape = RoundedCornerShape(8.dp),
                         colors = CardDefaults.cardColors(containerColor = Color.White),
                         elevation = CardDefaults.cardElevation(8.dp)
                     ) {


                         Row {
                             Text(
                                 text = task.empId, style = TextStyle(
                                     fontWeight = FontWeight.Normal,
                                     fontSize = 20.sp
                                 )
                             )
                             Spacer(modifier = Modifier.padding(10.dp))
                             Text(
                                 text = task.tasks,
                                 style = TextStyle(
                                     fontWeight = FontWeight.Normal,
                                     fontSize = 18.sp
                                 )
                             )
                             Spacer(modifier = Modifier.padding(10.dp))
                             Text(
                                 text = task.Date,
                                 style = TextStyle(
                                     fontWeight = FontWeight.Normal,
                                     fontSize = 15.sp
                                 )
                             )

                         }

                     }
                 }
                 }

             }
         }

         }
         if(isLoading==1){

                 Card(
                     modifier = Modifier
                         .size(500.dp)
                         .padding(30.dp),
                     shape = RoundedCornerShape(8.dp),
                     colors = CardDefaults.cardColors(containerColor = Color.White), // Use CardDefaults for containerColor
                     elevation = CardDefaults.cardElevation(8.dp),

                     ) {


                     Spacer(modifier = Modifier.padding(30.dp))
                     Box(
                         modifier = Modifier.fillMaxSize(),
                         contentAlignment = Alignment.Center
                     ) {

                         Column(modifier = Modifier
                             .padding(16.dp)
                             .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp) ) {
                             Text(
                                 text = "Adding Employees",
                                 style = TextStyle(fontWeight = FontWeight.Normal)
                             )
                             OutlinedTextField(
                                 value = inputtext, onValueChange = {
                                 inputtext = it
                             },label = { Text("Employee ID") },
                                 placeholder = { Text("Employee ID") }, shape = RoundedCornerShape(8.dp),
                                 )


                             OutlinedTextField(value = inputtext1, onValueChange = {
                                 inputtext1 = it
                             },label = { Text("Employee Name") },
                                 placeholder = { Text("Employee Name") }, shape = RoundedCornerShape(8.dp),
                                 )
                             Button(onClick = {
                                 employeeViewModel.AddEmployess(Employees(inputtext, inputtext1, 0))
                                  Toast.makeText(context,"Added Employees",Toast.LENGTH_LONG).show()
                                  navController.navigate("MainScreen")
                             }, modifier = Modifier.padding(16.dp),
                                 colors = ButtonDefaults.buttonColors(
                                     containerColor = Color.Blue, // Background color
                                     contentColor = Color.White // Text color
                                 ),
                                 shape = RoundedCornerShape(12.dp), // Button shape
                                 elevation = ButtonDefaults.elevatedButtonElevation(
                                     defaultElevation = 8.dp, // Elevation when the button is not pressed
                                     pressedElevation = 12.dp // Elevation when the button is pressed
                                 ),
                                 contentPadding = ButtonDefaults.ContentPadding) {
                                 Text(text = "Add")
                             }
                             Button(onClick = {
                                 navController.navigate("MainScreen")
                             }, modifier = Modifier.padding(16.dp),
                                 colors = ButtonDefaults.buttonColors(
                                     containerColor = Color.Blue, // Background color
                                     contentColor = Color.White // Text color
                                 ),
                                 shape = RoundedCornerShape(12.dp), // Button shape
                                 elevation = ButtonDefaults.elevatedButtonElevation(
                                     defaultElevation = 8.dp, // Elevation when the button is not pressed
                                     pressedElevation = 12.dp // Elevation when the button is pressed
                                 ),
                                 contentPadding = ButtonDefaults.ContentPadding) {
                                 Text(text = "Back")
                             }

                         }
                     }
                 }


         }

        }
    }
}


