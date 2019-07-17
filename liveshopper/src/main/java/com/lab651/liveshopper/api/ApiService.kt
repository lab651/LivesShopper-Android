package com.lab651.liveshopper.api

import com.lab651.liveshopper.task.model.*
import retrofit2.Call
import retrofit2.http.*


/**
 * The Live Shopper API service.
 */
interface ApiService {

    /**
     * Delete a campaign for a specified id
     *
     * @param id The id for a campaign
     */
    @DELETE("campaigns/{id}")
    fun deleteCampaignById(
        @Path("id") id: String
    ): Call<Campaign>

    /**
     * Get a campaign for a specified id
     *
     * @param id The id for a campaign
     */
    @GET("campaigns/{id}")
    fun getCampaignById(
        @Path("id") id: String
    ): Call<Campaign>

    /**
     * Create a campaign
     *
     * @param id The id for a campaign
     */
    @POST("campaigns/")
    fun insertCampaign(
        @Body campaign: Campaign
    ): Call<Campaign>

    /**
     * Get a campaigns for authenticated user
     *
     */
    @GET("campaigns")
    fun getCampaigns(
    ): Call<List<Campaign>>

    /**
     * Update a campaign for a specified id
     *
     * @param id The id for a campaign
     * @param campaign the campaign to update.
     */
    @PUT("campaigns/{id}")
    fun updateCampaignById(
        @Path("id") id: String,
        @Body campaign: Campaign
    ): Call<Campaign>

    /**
     * Return created tasks
     *
     * @param latitude
     * @param longitude
     * @param radius
     * @param minDistance
     */
    @GET("generate/tasks")
    fun generateTasks(
        @Query("lat") latitude: String,
        @Query("lng") longitude: String,
        @Query("rad") radius: String,
        @Query("min") minDistance: String,
        @Query("cid") campaignId: String,
        @Query("lid") locationId: String
    ): Call<GenerateTaskResponse>

    /**
     * Delete a location by the specified id
     *
     * @param id The id for the location.
     */
    @DELETE("locations/{id}")
    fun deleteLocationById(
        @Path("id") id: String
    ): Call<LsLocation>

    /**
     * Get a location for a specified id
     *
     * @param id The id for a location.
     */
    @GET("locations/{id}")
    fun getLocationById(
        @Path("id") id: String
    ): Call<LsLocation>

    /**
     * Get a locations for a specified search term
     *
     * @param searchTerm The search term to filer results.
     */
    @GET("locations/{searchTerm}")
    fun searchLocations(
        @Path("searchTerm") searchTerm: String
    ): Call<List<LsLocation>>

    /**
     * Return created locations
     *
     * @param latitude
     * @param longitude
     * @param radius
     * @param minDistance
     */
    @GET("locations/find")
    fun getLocations(
        @Query("lat") latitude: String,
        @Query("long") longitude: String,
        @Query("radius") radius: String,
        @Query("min") minDistance: String
    ): Call<List<LsLocation>>

    /**
     * Create a location
     *
     * @param location The location to create.
     */
    @POST("locations")
    fun createLocation(
        @Body location: LsLocation
    ): Call<LsLocation>

    /**
     * Update a location for a specified id
     *
     * @param id The id for the location to update.
     * @param location The model of the location to update.
     */
    @PUT("locations/{id}")
    fun updateLocation(
        @Path("id") id: String,
        @Body location: LsLocation
    ): Call<LsLocation>

    /**
     * Delete a question for a specified id
     *
     * @param id for the question
     */
    @DELETE("questions/{id}")
    fun deleteQuestion(
        @Path("id") id: String
    ): Call<Question>

    /**
     * Get a question for a specified id
     *
     * @param id The id for a question
     */
    @GET("questions/{id}")
    fun getQuestion(
        @Path("id") id: String
    ): Call<Question>

    /**
     * Create a question
     *
     * @param question The question model to create.
     */
    @POST("questions/")
    fun createQuestion(
        @Body question: Question
    ): Call<Question>

    /**
     * Get a question for authenticated user
     *
     */
    @GET("questions")
    fun getQuestions(
    ): Call<List<Question>>

    /**
     * Update a question for a specified id
     *
     * @param id The id of the question.
     * @param body The question model to update.
     */
    @PUT("questions/{id}")
    fun updateQuestion(
        @Path("id") id: String,
        @Body question: Question
    ): Call<Question>

    /**
     * Permanently deletes a task response.
     *
     * @param tid The id of the task.
     * @param id Then id of the response.
     */
    @DELETE("tasks/{tid}/responses/{id}")
    fun deleteTaskResponse(
        @Path("tid") taskId: String,
        @Path("id") id: String
    ): Call<TaskResponse>

    /**
     * Retrieves a task response
     *
     * @param tid The id of the task.
     * @param id Then id of the response.
     */
    @GET("tasks/{tid}/responses:/{id}")
    fun getTaskResponse(
        @Path("tid") taskId: String,
        @Path("id") id: String
    ): Call<TaskResponse>

    /**
     * Get all responses for a task
     *
     * @param taskId The id of the task.
     */
    @GET("tasks/{tid}/responses")
    fun getTaskResponses(
        @Path("tid") taskId: String
    ): Call<List<TaskResponse>>

    /**
     * Create a new TaskResponse.
     *
     * @param taskResponse The model of the task response to create.
     * @param taskId The id of the task.
     */
    @POST("tasks/{tid}/responses")
    fun createTaskResponse(
        @Body taskResponse: TaskResponse,
        @Path("tid") taskId: String
    ): Call<TaskResponse>

    /**
     * Update a TaskResponse for a specified id
     *
     * @param tid The id of the task.
     * @param id Then id of the response.
     * @param taskResponse The model of the task response to create.
     */
    @PUT("tasks/{tid}/response/{id}")
    fun updateTaskResponse(
        @Path("tid") taskId: String,
        @Path("id") id: String,
        @Body taskResponse: TaskResponse
    ): Call<TaskResponse>

    /**
     * Deletes the specified task
     *
     * @param id The id for a task.
     */
    @DELETE("tasks/{id}")
    fun deleteTask(
        @Path("id") id: String
    ): Call<Task>

    /**
     * Retrieves a specific task
     *
     * @param id The id for a task.
     */
    @GET("tasks/{id}")
    fun getTask(
        @Path("id") id: String
    ): Call<Task>

    /**
     * Gets completed tasks
     *
     * @param latitude
     * @param longitude
     * @param radius
     * @param minDistance
     * @param campaignId
     * @param locationId
     */
    @GET("tasks")
    fun getTasks(
        @Query("lat") latitude: String,
        @Query("long") longitude: String,
        @Query("radius") radius: String,
        @Query("min") minDistance: String,
        @Query("cid") campaignId: String,
        @Query("lid") locationId: String
    ): Call<List<Task>>

    /**
     * Create a task
     *
     * @param body The model of the task to create.
     */
    @POST("tasks/")
    fun createTask(
        @Body task: Task
    ): Call<Task>

    /**
     * Update a specific task
     *
     * @param tid task Id
     * @param id for the task response
     */
    @PUT("tasks/{id}")
    fun updateTask(
        @Path("id") id: String,
        @Body task: Task
    ): Call<Task>

    /**
     * Permanently deletes a user
     *
     * @param id The id of the user to delete.
     */
    @DELETE("users/{id}")
    fun deleteUser(
        @Path("id") id: String
    ): Call<User>

    /**
     * Retrieves a user
     *
     * @param id The id for the user being retrieved.
     */
    @GET("user/{id}")
    fun getUser(
        @Path("id") id: String
    ): Call<User>

    /**
     * Gets a clients users
     *
     */
    @GET("users")
    fun getUsers(
    ): Call<List<User>>

    /**
     * Create a new user.
     *
     * @param body The model of the user to create.
     */
    @POST("tasks")
    fun createUser(
        @Body user: User
    ): Call<User>

    /**
     * Get tasks completed by user
     *
     * @param id The id of the user.
     */
    @PUT("users/{id}/tasks")
    fun getUsersTasks(
        @Path("id") id: String
    ): Call<List<Task>>
}
