package com.example.teammanagement.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employees(
    @PrimaryKey
    var empId: String,

    var empName:String,

    var empImage:Int
)
