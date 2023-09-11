package com.example.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.example.sqlite.Goal
import com.example.sqlite.GoalAdapter
import com.example.sqlite.GoalRepository

class MainActivity : AppCompatActivity() {
    private val goalRepository by lazy { GoalRepository(this) }

    // Declare EditTexts and Button as global variables
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize EditTexts and Button
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        addButton = findViewById(R.id.addButton)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Set up RecyclerView adapter and layout manager
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = GoalAdapter()
        recyclerView.adapter = adapter

        // Load the list of goals when the activity is created
        adapter.updateGoals(goalRepository.getAllGoals())

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            if (title.isNotEmpty()) {
                val goal = Goal(0, title, description, false)
                goalRepository.addGoal(goal)
                adapter.updateGoals(goalRepository.getAllGoals())
                clearInputFields()
            }
        }

        adapter.setOnGoalCompletedListener { goalId ->
            goalRepository.markGoalAsCompleted(goalId)
            adapter.updateGoals(goalRepository.getAllGoals())
        }
    }

    private fun clearInputFields() {
        titleEditText.text.clear()
        descriptionEditText.text.clear()
    }
}
