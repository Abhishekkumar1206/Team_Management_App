package com.example.teammanagement

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teammanagement.DB.AppDatabase

object DatabaseProvider {
    @Volatile
    private var database: AppDatabase? = null

    fun getDatabase(application: Context): AppDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java,
                "TeamDB"
            ).addMigrations(MIGRATION_1_2).build()
            database = instance
            instance
        }
    }
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add new table here
            database.execSQL(
                "CREATE TABLE `Todo` (`todoId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `empId` TEXT NOT NULL, `tasks` TEXT NOT NULL)"
            )
        }
    }
}
