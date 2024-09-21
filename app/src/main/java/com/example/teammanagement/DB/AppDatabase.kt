package com.example.teammanagement.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teammanagement.Controller.EmployeeViewModel
import com.example.teammanagement.Converters
import com.example.teammanagement.DAO.TeamDAO
import com.example.teammanagement.Entities.Employees
import com.example.teammanagement.Entities.Todos

@Database(entities = [Employees::class,Todos::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun teamdao(): TeamDAO

}