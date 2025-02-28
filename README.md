# SQLite Goal Tracker App

This is a simple Android app built with Kotlin and SQLite for managing personal goals. It allows users to add, view, update, and mark goals as completed.

## Features
- **Add Goal:** Capture goal title and description and add it to the SQLite database.
- **View Goals:** Display the list of all goals in a RecyclerView.
- **Mark Goal as Completed:** Check off goals when completed.
- **Data Persistence:** Goals are stored locally using SQLite.

## Technologies Used
- Kotlin
- SQLite
- RecyclerView
- Android Jetpack

## How to Use
1. Enter a goal title and description, then tap the "Add" button.
2. View goals in the RecyclerView.
3. Check the box next to a goal to mark it as completed.

## Project Structure
- **Goal.kt:** Data class representing a goal.
- **GoalAdapter.kt:** RecyclerView adapter for displaying goals.
- **GoalDatabaseHelper.kt:** SQLite database helper for managing goal data.
- **GoalRepository.kt:** Repository handling database operations.
- **MainActivity.kt:** Main activity for user interaction.

## Future Enhancements
- Add goal editing functionality.
- Implement goal deletion.
- Improve UI/UX design.
- Add reminders or notifications for pending goals.

---
This project serves as a simple introduction to building an Android app with local data storage using SQLite.

