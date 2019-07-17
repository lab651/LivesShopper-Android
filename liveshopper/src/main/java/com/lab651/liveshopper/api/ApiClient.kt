package com.lab651.liveshopper.api

import com.lab651.liveshopper.BuildConfig
import com.lab651.liveshopper.task.model.*
import com.lab651.liveshopper.util.HttpUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * The Live Shopper API Client. This class wraps the API service
 */
object ApiClient {

    private val enableLogging = BuildConfig.DEBUG
    private val service by lazy { createService() }

    private val BASE_URL_DEV = "https://api.liveshopper.com/v2-dev/"
    private val BASE_URL_PROD = "https://api.liveshopper.com/v2/"

    private fun createService(): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_DEV)
            .addConverterFactory(GsonConverterFactory.create())
            .client(HttpUtil.getOkhttpClient(enableLogging))
            .build()

        return retrofit.create(ApiService::class.java)
    }


    /*****************************************/
    /**     Task Response Resources         **/
    /*****************************************/

    /**
     * Permanently deletes a task response.
     *
     * @param taskId Task Id
     * @param id
     */
    @JvmOverloads
    @JvmStatic
    fun deleteTaskResponse(
        taskId: String,
        id: String,
        onResponse: (TaskResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.deleteTaskResponse(taskId, id)
            .enqueue(ApiCallback<TaskResponse>(onResponse, onFailure))
    }

    /**
     * Retrieves a task response
     *
     * @param tid Task Id
     * @param id The id for a task
     */
    @JvmOverloads
    @JvmStatic
    fun retrieveTaskResponse(
        taskId: String,
        id: String,
        onResponse: (TaskResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getTaskResponse(taskId, id)
            .enqueue(ApiCallback<TaskResponse>(onResponse, onFailure))
    }

    /**
     * Get all responses for a task
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getAllTaskResponses(
        taskId: String,
        onResponse: (List<TaskResponse>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getTaskResponses(taskId)
            .enqueue(ApiCallback<List<TaskResponse>>(onResponse, onFailure))
    }

    /**
     * Create a new response.
     *
     */
    @JvmOverloads
    @JvmStatic
    fun createTaskResponse(
        taskResponse: TaskResponse,
        taskId: String,
        onResponse: (TaskResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.createTaskResponse(taskResponse, taskId)
            .enqueue(ApiCallback<TaskResponse>(onResponse, onFailure))
    }

    /**
     * Update a TaskResponse for a specified id
     *
     * @param tid task Id
     * @param id for the task response
     */
    @JvmOverloads
    @JvmStatic
    fun updateTaskResponseById(
        taskId: String,
        id: String,
        taskResponse: TaskResponse,
        onResponse: (TaskResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.updateTaskResponse(id, taskId, taskResponse)
            .enqueue(ApiCallback<TaskResponse>(onResponse, onFailure))
    }


    /*****************************************/
    /**        Campaign Resources           **/
    /*****************************************/

    /**
     * Delete a campaign for a specified id
     *
     * @param id The id for a campaign
     */
    @JvmOverloads
    @JvmStatic
    fun deleteCampaignById(
        id: String,
        onResponse: (Campaign) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.deleteCampaignById(id)
            .enqueue(ApiCallback<Campaign>(onResponse, onFailure))
    }

    /**
     * Get a campaign for a specified id
     *
     * @param id The id for a campaign
     */
    @JvmOverloads
    @JvmStatic
    fun getCampaignById(
        id: String,
        onResponse: (Campaign) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getCampaignById(id)
            .enqueue(ApiCallback<Campaign>(onResponse, onFailure))
    }

    /**
     * Create a campaign
     *
     * @param id The id for a campaign
     */
    @JvmOverloads
    @JvmStatic
    fun createNewCampaign(
        campaign: Campaign,
        onResponse: (Campaign) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.insertCampaign(campaign)
            .enqueue(ApiCallback<Campaign>(onResponse, onFailure))
    }

    /**
     * Get a campaigns for authenticated user
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getAllCampaigns(
        onResponse: (List<Campaign>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getCampaigns()
            .enqueue(ApiCallback<List<Campaign>>(onResponse, onFailure))
    }

    /**
     * Update a campaign for a specified id
     *
     * @param id The id for a campaign
     */
    @JvmOverloads
    @JvmStatic
    fun updateCampaignById(
        id: String,
        campaign: Campaign,
        onResponse: (Campaign) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.updateCampaignById(id, campaign)
            .enqueue(ApiCallback<Campaign>(onResponse, onFailure))
    }


    /*****************************************/
    /**        Generate Resources           **/
    /*****************************************/

    /**
     * Return all available tasks within the region
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getAvailableTasks(
        latitude: String,
        longitude: String,
        radius: String,
        minDistance: String,
        campaignId: String = "",
        locationId: String = "",
        onResponse: (GenerateTaskResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.generateTasks(latitude, longitude, radius, minDistance, campaignId, locationId)
            .enqueue(ApiCallback<GenerateTaskResponse>(onResponse, onFailure))
    }


    /*****************************************/
    /**        Location Resources          **/
    /*****************************************/

    /**
     * Create a new location
     *
     * @param id The id for a location
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     */
    @JvmOverloads
    @JvmStatic
    fun createNewLocation(
        id: String,
        onResponse: (LsLocation) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.deleteLocationById(id)
            .enqueue(ApiCallback<LsLocation>(onResponse, onFailure))
    }


    /**
     * Get a location for a specified id
     *
     * @param id The id for a location
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     * */
    @JvmOverloads
    @JvmStatic
    fun getLocationById(
        id: String,
        onResponse: (LsLocation) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getLocationById(id)
            .enqueue(ApiCallback<LsLocation>(onResponse, onFailure))
    }

    /**
     * Get a locations for a specified search term
     *
     * @param id The id for a location
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     *
     */
    @JvmOverloads
    @JvmStatic
    fun searchLocations(
        searchQuery: String,
        onResponse: (List<LsLocation>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.searchLocations(searchQuery)
            .enqueue(ApiCallback<List<LsLocation>>(onResponse, onFailure))
    }

    /**
     * Return created locations
     *
     * @param latitude the latitude of the center of the search radius.
     * @param longitude longitude of the center of the search radius.
     * @param radius the radius to search within.
     * @param minDistance the minimum distance the user can be from the task location.
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     */
    @JvmOverloads
    @JvmStatic
    fun getAllLocations(
        latitude: String,
        longitude: String,
        radius: String,
        minDistance: String,
        onResponse: (List<LsLocation>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getLocations(latitude, longitude, radius, minDistance)
            .enqueue(ApiCallback<List<LsLocation>>(onResponse, onFailure))
    }

    /**
     * Create a location
     *
     * @param location the location model to create.
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     */
    @JvmOverloads
    @JvmStatic
    fun createNewLocation(
        location: LsLocation,
        onResponse: (LsLocation) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.createLocation(location)
            .enqueue(ApiCallback<LsLocation>(onResponse, onFailure))
    }

    /**
     * Update a location for a specified id
     *
     * @param id The id for the location
     * @param location the location model to update.
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     */
    @JvmOverloads
    @JvmStatic
    fun updateLocationById(
        id: String,
        location: LsLocation,
        onResponse: (LsLocation) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.updateLocation(id, location)
            .enqueue(ApiCallback<LsLocation>(onResponse, onFailure))
    }

    /*****************************************/
    /**        Question Resources           **/
    /*****************************************/

    /**
     * Delete a question for a specified id
     *
     * @param id for the question
     * @param onResponse the callback for a successful response.
     * @param onFailure the callback for an error.
     */
    @JvmOverloads
    @JvmStatic
    fun deleteQuestionById(
        id: String,
        onResponse: (Question) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.deleteQuestion(id)
            .enqueue(ApiCallback<Question>(onResponse, onFailure))
    }

    /**
     * Get a question for a specified id
     *
     * @param id The id for a campaign
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getQuestionById(
        id: String,
        onResponse: (Question) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getQuestion(id)
            .enqueue(ApiCallback<Question>(onResponse, onFailure))
    }

    /**
     * Create a question
     *
     */
    @JvmOverloads
    @JvmStatic
    fun createQuestion(
        question: Question,
        onResponse: (Question) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.createQuestion(question)
            .enqueue(ApiCallback<Question>(onResponse, onFailure))
    }

    /**
     * Get a question for authenticated user
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getAllQuestions(
        onResponse: (List<Question>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getQuestions()
            .enqueue(ApiCallback<List<Question>>(onResponse, onFailure))
    }

    /**
     * Update a question for a specified id
     *
     * @param id for the question
     */
    @JvmOverloads
    @JvmStatic
    fun updateQuestionById(
        id: String,
        question: Question,
        onResponse: (Question) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.updateQuestion(id, question)
            .enqueue(ApiCallback<Question>(onResponse, onFailure))
    }

    /*****************************************/
    /**           Task Resources            **/
    /*****************************************/

    /**
     * Deletes the specified task
     *
     * @param id
     */
    @JvmOverloads
    @JvmStatic
    fun deleteTaskById(
        id: String,
        onResponse: (Task) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.deleteTask(id)
            .enqueue(ApiCallback<Task>(onResponse, onFailure))
    }

    /**
     * Retrieves a specific task
     *
     * @param id The id for a task
     */
    @JvmOverloads
    @JvmStatic
    fun getTaskById(
        id: String,
        onResponse: (Task) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getTask(id)
            .enqueue(ApiCallback<Task>(onResponse, onFailure))
    }


    /**
     * Gets completed tasks for a region
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getCompletedTasks(
        latitude: String,
        longitude: String,
        radius: String,
        minDistance: String,
        campaignId: String,
        locationId: String,
        onResponse: (List<Task>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getTasks(latitude, longitude, radius, minDistance, campaignId, locationId)
            .enqueue(ApiCallback<List<Task>>(onResponse, onFailure))
    }

    /**
     * Create a task
     *
     * @param task The task to claim.
     */
    @JvmOverloads
    @JvmStatic
    fun claimTask(
        task: Task,
        onResponse: (Task) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.createTask(task)
            .enqueue(ApiCallback<Task>(onResponse, onFailure))
    }

    /**
     * Update a specific task
     *
     * @param tid task Id
     * @param id for the task response
     */
    @JvmOverloads
    @JvmStatic
    fun updateTaskById(
        task: Task,
        taskId: String,
        onResponse: (Task) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.updateTask(taskId, task)
            .enqueue(ApiCallback<Task>(onResponse, onFailure))
    }


    /*****************************************/
    /**          User Resources             **/
    /*****************************************/

    /**
     * Permanently deletes a user
     *
     * @param id
     */
    @JvmOverloads
    @JvmStatic
    fun deleteUser(
        id: String,
        onResponse: (User) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.deleteUser(id)
            .enqueue(ApiCallback<User>(onResponse, onFailure))
    }


    /**
     * Retrieves a user
     *
     * @param id The id for a user
     */
    @JvmOverloads
    @JvmStatic
    fun getuser(
        id: String,
        onResponse: (User) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getUser(id)
            .enqueue(ApiCallback<User>(onResponse, onFailure))
    }

    /**
     * Gets a clients users
     *
     */
    @JvmOverloads
    @JvmStatic
    fun getUsers(
        onResponse: (List<User>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getUsers()
            .enqueue(ApiCallback<List<User>>(onResponse, onFailure))
    }

    /**
     * Create a new user.
     *
     */
    @JvmOverloads
    @JvmStatic
    fun createUser(
        user: User,
        onResponse: (User) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.createUser(user)
            .enqueue(ApiCallback<User>(onResponse, onFailure))
    }

    /**
     * Get tasks completed by user
     *
     * @param id for the user
     */
    @JvmOverloads
    @JvmStatic
    fun getCompletedTasksForUser(
        id: String,
        onResponse: (List<Task>) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getUsersTasks(id)
            .enqueue(ApiCallback<List<Task>>(onResponse, onFailure))
    }

    private class ApiCallback<T>(
        val onResponse: (T) -> Unit,
        val onFailure: (Throwable) -> Unit
    ) : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                onResponse(it)
            } ?: onFailure(Exception("Response body is null"))
        }
    }
}
