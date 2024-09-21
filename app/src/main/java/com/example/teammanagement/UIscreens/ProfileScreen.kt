package com.example.teammanagement.UIscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teammanagement.Controller.EmployeeViewModel
import com.example.teammanagement.Entities.Employees
import com.example.teammanagement.R
import java.time.LocalDate

@Composable
fun ProfileScreen(navController: NavController){

    val employeeViewModel: EmployeeViewModel = viewModel()
    val emplist by employeeViewModel.EmpList.observeAsState(emptyList())

    Box(modifier=Modifier.fillMaxSize()) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // Grid with 2 columns
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(emplist) { employee ->
            EmployeeCard(employee, navController)
        }
    }

            Button(
                onClick = {
                    navController.navigate("MainScreen")
                }, modifier = Modifier.padding(100.dp).align(Alignment.BottomEnd),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue, // Background color
                    contentColor = Color.White // Text color
                ),
                shape = RoundedCornerShape(12.dp), // Button shape
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 8.dp, // Elevation when the button is not pressed
                    pressedElevation = 12.dp // Elevation when the button is pressed
                ),
                contentPadding = ButtonDefaults.ContentPadding
            ) {
                Text(text = "Back")
            }
    }
 }

@Composable
fun EmployeeCard(employee: Employees,navController: NavController) {
    val employeeViewModel: EmployeeViewModel = viewModel()
    val scrollState = remember { ScrollState(0) }
    Spacer(modifier=Modifier.padding(20.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(8.dp)
            .background(Color.White)
            .clickable { navController.navigate("todos/${employee.empId}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,  // Center content horizontally
            verticalArrangement = Arrangement.Center  // Center content vertically
        ) {
        Image(
            painter = painterResource(id = R.drawable.blankprofile),  // Use the image from resources
            contentDescription = "Employee Image",
            contentScale = ContentScale.Crop,  // Crop the image to fit
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)

        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "ID: ${employee.empId}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = employee.empName, fontSize = 14.sp)
            }
            // Delete Icon with click action
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_delete),  // Delete icon from resources
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                        employeeViewModel.DeleteEmployees(employee.empId)
                    }

            )
        }
    }

    }
}
