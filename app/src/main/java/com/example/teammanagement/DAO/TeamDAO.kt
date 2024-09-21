package com.example.teammanagement.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.teammanagement.Entities.Employees
import com.example.teammanagement.Entities.Todos

@Dao
interface TeamDAO {
    @Query("Select * from Employees")
    fun getAllEmployees(): LiveData<List<Employees>>

    @Insert
    suspend fun addEmployees(emp: Employees)

    @Query("Delete from Employees where empId=:empId")
    suspend fun delEmployees(empId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodos(todos: Todos)

    @Query("Delete from todos where todoId=:todoId")
    suspend fun delTodos(todoId: Int)

    @Query("Select * from todos where empId=:empId")
    fun getallTodos(empId:String):LiveData<List<Todos>>

   @Query("SELECT * FROM todos WHERE DATE(Date) BETWEEN DATE(CURRENT_DATE) AND DATE(CURRENT_DATE, '+5 days')")
    fun getTodosExceedingFiveDays():LiveData<List<Todos>>


}