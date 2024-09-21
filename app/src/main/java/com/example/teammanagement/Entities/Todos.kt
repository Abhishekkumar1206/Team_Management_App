package com.example.teammanagement.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todos(

    @PrimaryKey(autoGenerate = true)
    var todoId :Int=0,
    var empId:String,
    var tasks:String,
    var Date:String,

)
