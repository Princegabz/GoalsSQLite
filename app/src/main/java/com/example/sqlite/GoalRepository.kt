package com.example.sqlite

// GoalRepository.kt
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class GoalRepository(context: Context) {
    private val dbHelper = GoalDatabaseHelper(context)

    fun addGoal(goal: Goal) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", goal.title)
            put("description", goal.description)
            put("is_completed", if (goal.isCompleted) 1 else 0)
        }
        db.insert("goals", null, values)
        db.close()
    }

    fun getAllGoals(): List<Goal> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM goals", null)
        val goals = mutableListOf<Goal>()
        while (cursor.moveToNext()) {
            val id = cursor.getLong(0) // Assuming 'id' is the first column in the SELECT statement
            val title = cursor.getString(1) // Assuming 'title' is the second column
            val description = cursor.getString(2) // Assuming 'description' is the third column
            val isCompleted = cursor.getInt(3) == 1 // Assuming 'is_completed' is the fourth column

            goals.add(Goal(id, title, description, isCompleted))
        }
        cursor.close()
        db.close()
        return goals
    }

    fun markGoalAsCompleted(goalId: Long) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("is_completed", 1)
        }
        db.update("goals", values, "id = ?", arrayOf(goalId.toString()))
        db.close()
    }
}
