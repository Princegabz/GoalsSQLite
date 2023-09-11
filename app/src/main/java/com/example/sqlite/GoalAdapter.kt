package com.example.sqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GoalAdapter : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {
    private var goals: List<Goal> = emptyList()
    private var onGoalCompletedListener: ((Long) -> Unit)? = null

    // Declare views as global variables
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var completedCheckbox: CheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_goal, parent, false)
        return GoalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = goals[position]

        // Initialize views using findViewById
        val titleTextView = holder.itemView.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = holder.itemView.findViewById<TextView>(R.id.descriptionTextView)
        val completedCheckbox = holder.itemView.findViewById<CheckBox>(R.id.completedCheckbox)

        // Set the values for the views
        titleTextView.text = goal.title
        descriptionTextView.text = goal.description
        completedCheckbox.isChecked = goal.isCompleted

        completedCheckbox.setOnClickListener {
            onGoalCompletedListener?.invoke(goal.id)
        }
    }

    override fun getItemCount(): Int = goals.size

    fun updateGoals(newGoals: List<Goal>) {
        goals = newGoals
        notifyDataSetChanged()
    }

    fun setOnGoalCompletedListener(listener: (Long) -> Unit) {
        onGoalCompletedListener = listener
    }

    inner class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
