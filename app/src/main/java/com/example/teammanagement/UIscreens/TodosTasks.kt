package com.example.teammanagement.UIscreens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
//import com.example.teammanagement.Controller.TodoViewModel
import com.example.teammanagement.Controller.TodosViewModel
import com.example.teammanagement.Entities.Todos

@Composable
fun TodosTasks(empId: String, navController: NavController) {
    var tasks by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    val todosViewModel: TodosViewModel = viewModel()

    LaunchedEffect(empId) {
        todosViewModel.setEmpId(empId)
    }

    val todoLists by todosViewModel.todolist.observeAsState(emptyList())
    val scrollState = remember { ScrollState(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Employee ID: $empId", style = TextStyle(fontWeight = FontWeight.Normal))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tasks,
            onValueChange = { tasks = it },
            label = { Text("Tasks:") },
            placeholder = { Text("Enter Tasks") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date:") },
            placeholder = { Text("YYYY/MM/DD") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                todosViewModel.addTodo(empId, tasks, date)
                tasks = "" // Clear input after adding
                date = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todoLists) { todo ->
                TodosItem(item = todo, onDelete = { todosViewModel.Deltodo(todo.todoId) },navController)
            }
        }
        Button(onClick = { navController.navigate("profile") }
            , modifier = Modifier.padding(16.dp),
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

@Composable
fun TodosItem(item: Todos, onDelete: () -> Unit,navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = item.tasks, fontSize = 20.sp, color = Color.Black)
                Text(text = item.Date, fontSize = 16.sp, color = Color.Black.copy(alpha = 0.8f))
            }
            Button(onClick = onDelete
                , modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, // Background color
                    contentColor = Color.White // Text color
                ),
                shape = RoundedCornerShape(12.dp), // Button shape
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 8.dp, // Elevation when the button is not pressed
                    pressedElevation = 12.dp // Elevation when the button is pressed
                ),
                contentPadding = ButtonDefaults.ContentPadding) {
                Text(text = "Delete")
            }

        }
        Spacer(modifier = Modifier.weight(1f)) // Pushes the button to the bottom

        // Back Button

    }
}


