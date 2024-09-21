package com.example.teammanagement.Controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.teammanagement.Entities.Todos
import com.example.teammanagement.MainActivity
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class TodosViewModel: ViewModel() {

    private val _empId = MutableLiveData<String>()
    private val _todolist = MutableLiveData<List<Todos>>()
    val todolist: LiveData<List<Todos>> get() = _todolist

    private val tododao = MainActivity.appDB.teamdao()


    init {
        // Observe empId changes
        _empId.observeForever { id ->
            id?.let { fetchTodos(it) }
        }
    }

    // Fetch todos based on empId
    private fun fetchTodos(empId: String) {
        viewModelScope.launch {
            // Fetch todos from the database
            tododao.getallTodos(empId).asFlow().collect { todos ->
                _todolist.postValue(todos ?: emptyList())


            }

        }
    }

    // Set the employee ID and fetch the todos
    fun setEmpId(id: String) {
        _empId.value = id
    }

    // Add a new todo and refresh the list
    fun addTodo(empId: String, task: String,date:String) {
        viewModelScope.launch {
            tododao.addTodos(Todos(empId = empId, tasks = task,Date=date))
            fetchTodos(empId)
        }
    }
    fun Deltodo(todoId:Int){
        viewModelScope.launch{
            tododao.delTodos(todoId)
        }
    }
}