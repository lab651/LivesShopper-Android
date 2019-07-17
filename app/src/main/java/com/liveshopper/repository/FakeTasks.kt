package com.liveshopper.repository

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.lab651.liveshopper.task.model.Task


object FakeTasks {
    fun buildTasks(context: Context): List<Task> {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()

        val inputStream = context.assets.open("Tasks.json")
        return if (inputStream != null) {
            val json = inputStream.readBytes().toString(Charsets.UTF_8)
            inputStream.close()

            val listType = object : TypeToken<List<Task>>() {}.type
            gson.fromJson<List<Task>>(json, listType)
        } else {
            arrayListOf()
        }
    }
}
