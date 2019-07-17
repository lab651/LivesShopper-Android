package com.lab651.liveshopper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import android.widget.Toast
import com.lab651.liveshopper.api.ApiClient
import com.lab651.liveshopper.auth.AuthHelper
import com.lab651.liveshopper.task.model.Question
import com.lab651.liveshopper.task.model.Task
import com.lab651.liveshopper.task.model.TaskResponse
import java.io.ByteArrayOutputStream

@SuppressLint("StaticFieldLeak")
object LiveShopperAPI {

    const val BARCODE_SCAN = "barcodeScan"
    const val DATE_QUESTION = "date"
    const val OPEN_ENDED_QUESTION = "openEnded"
    const val SINGLE_ANSWER_QUESTION = "singleAnswer"
    const val MULTIPLE_ANSWERS_QUESTION = "multipleAnswers"
    const val STATE_COMPLETE = "complete"
    const val NUMERIC_OPEN_ENDED = "numericOpenEnded"
    const val RATING_SCALE_QUESTION = "ratingScale"
    const val RATING_SCALE_TEXT_QUESTION = "ratingScaleWithText"
    const val TIME_QUESTION = "time"

    var context: Context? = null
    var clientId: String? = null

    /** Scale factor to apply to images before submitting an image along with a question */
    var imageScale: Float = 0.5f

    /** Default value (in miles) to use for minimum search radius. */
    var minimumSearchRadius: Double? = null

    /** Default value (in miles) to use for maximum search radius. */
    var searchRadius: Float = 20f

    /** Whether to report distances for `Task` models in metric. */
    var useMetric: Boolean = false

    /** The current task being claimed. */
    var claimedTask: Task? = null

    /** The task response for the current question. */
    var taskResponse: TaskResponse? = null

    /** The current question being answered in the claimed task. */
    var currentQuestion: Question? = null

    /** The image data associated with the current question */
    private var imageJpegData: String? = null


    /**
     * Log in to authenticate all LiveShopperAPI calls with a User.
     *
     * @param userID Unique identifier to use to authenticate a user account.
     * @param redirectURL Redirect URL associated with your Tenant.
     * @param completionSuccess Indicates success of the request.
     * @param completionFailure Indicates failure of the request.
     */
    fun login(
        userID: String,
        redirectURL: String,
        completionSuccess: () -> Unit,
        completionFailure: (Throwable) -> Unit
    ) {
        if (context != null && clientId != null)
            AuthHelper(context!!, userID, clientId!!, redirectURL).authenticate(completionSuccess, completionFailure)
        else {
            throwNotInitializedError()
            completionFailure(Throwable(LiveShopperError.SDK_NOT_INITIALIZED.message))
        }
    }

    /**
     * Log in to authenticate all LiveShopperAPI calls with a User.
     *
     * @param taskKey Unique identifier for the `Task` object.
     * @param completionSuccess Indicates success of the request.
     * @param completionFailure Indicates failure of the request.
     */
    fun getTask(
        taskKey: String,
        completionSuccess: (task: Task) -> Unit,
        completionFailure: (message: String) -> Unit
    ) {

    }

    /**
     * Create a claimed task for user
     *
     * @param task The task to claim.
     * @param completionSuccess Indicates success of the request.
     * @param completionFailure Indicates failure of the request.
     */
    fun claimTask(
        task: Task,
        completionSuccess: (Task) -> Unit,
        completionFailure: (Throwable) -> Unit
    ) {
        ApiClient.claimTask(task, completionSuccess, completionFailure)
    }

    /**
     * Returns the next question if available otherwise returns null.
     *
     * @param questionID The id for the next question to answer.
     *
     */
    fun getNextQuestion(questionID: String): Question? {
        val q = claimedTask?.questions?.filter { it.parentKey == questionID }
        currentQuestion = null

        if (!q.isNullOrEmpty()) currentQuestion = q[0]

        return currentQuestion
    }

    /**
     * Handles submitting an answer to the API client for the current question.
     *
     * @param response The taskResponse
     *
     */
    fun submitAnswer(response: TaskResponse, onSubmissionSuccess: (TaskResponse) -> Unit) {
        claimedTask?.let {
            ApiClient.createTaskResponse(response, it.key,
                {
                    onSubmissionSuccess(it)
                }, {
                    // Error submitting answer
                    Toast.makeText(context, "Error submitting Answer!", Toast.LENGTH_LONG).show()
                })
        }
    }

    fun populateTaskResponse(): TaskResponse {
        return TaskResponse(
            question = currentQuestion,
            owners = claimedTask?.owners,
            taskKey = claimedTask?.key,
            state = "",
            base64 = imageJpegData
        )
    }

    fun setImageAndScale(data: ByteArray): Bitmap {
        val bitMap = BitmapFactory.decodeByteArray(data, 0, data.size)

        val scaledBitmap =
            Bitmap.createScaledBitmap(bitMap, (bitMap.width * 0.8).toInt(), (bitMap.height * 0.8).toInt(), true)

        val stream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        // Store the data in the member for use when submitting the user answer in the task response
        imageJpegData = "data:image/jpeg;base64," + Base64.encodeToString(byteArray, Base64.NO_WRAP)

        return rotateImage(scaledBitmap, 90f)
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }

    private fun throwNotInitializedError() {
        throw Throwable("Live Shopper SDK not initialized. Please see documentation for proper usage.")
    }

}
