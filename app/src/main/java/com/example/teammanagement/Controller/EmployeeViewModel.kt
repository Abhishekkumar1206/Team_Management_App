package com.example.teammanagement.Controller


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teammanagement.Entities.Employees
import com.example.teammanagement.Entities.Todos
import com.example.teammanagement.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel() : ViewModel() {

    val appdb= MainActivity.appDB

    val Empdao=appdb.teamdao()

    val EmpList: LiveData<List<Employees>> = Empdao.getAllEmployees()

    val todosExceedingFiveDays: LiveData<List<Todos>> = Empdao.getTodosExceedingFiveDays()

    fun AddEmployess(employees: Employees){
        viewModelScope.launch(Dispatchers.IO) {
            Empdao.addEmployees(employees)
        }
    }
    fun DeleteEmployees(EmpId: String){
        viewModelScope.launch(Dispatchers.IO){
            Empdao.delEmployees(EmpId)
        }
    }

}

