package com.liveshopper.feature.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lab651.liveshopper.LiveShopperAPI
import com.liveshopper.feature.task.taskoverview.TaskOverviewFragment
import com.liveshopper.repository.TaskRepository

class TaskActivity : AppCompatActivity() {
    //lateinit var currentQuestion: Question
    //lateinit var taskResponse: TaskResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskId = intent.getStringExtra(EXTRA_TASK_ID)
        // Pull the selected claimedTask from the claimedTask repository and set it on the live shopper API singleton.
        LiveShopperAPI.claimedTask = TaskRepository.getTask(taskId)

        // Launch the overview fragment.
        // Display the fragment as the main content.
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, TaskOverviewFragment())
            .commit()
    }

    init {
        orderCount = 0
    }

    companion object {
        const val EXTRA_TASK_ID = "taskId"
        var orderCount = 0
    }
}