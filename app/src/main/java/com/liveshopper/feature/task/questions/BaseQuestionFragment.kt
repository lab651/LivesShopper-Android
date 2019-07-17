package com.liveshopper.feature.task.questions

import android.content.Context.SENSOR_SERVICE
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.hardware.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lab651.liveshopper.LiveShopperAPI
import com.lab651.liveshopper.LiveShopperAPI.BARCODE_SCAN
import com.lab651.liveshopper.LiveShopperAPI.DATE_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.MULTIPLE_ANSWERS_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.NUMERIC_OPEN_ENDED
import com.lab651.liveshopper.LiveShopperAPI.OPEN_ENDED_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.RATING_SCALE_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.RATING_SCALE_TEXT_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.SINGLE_ANSWER_QUESTION
import com.lab651.liveshopper.LiveShopperAPI.STATE_COMPLETE
import com.lab651.liveshopper.LiveShopperAPI.TIME_QUESTION
import com.lab651.liveshopper.task.model.PhotoCaptureOptions
import com.lab651.liveshopper.task.model.Question
import com.lab651.liveshopper.task.model.Task
import com.lab651.liveshopper.task.model.TaskResponse
import com.lab651.liveshopper.util.CameraPreview
import com.liveshopper.feature.task.TaskActivity
import kotlinx.android.synthetic.main.base_question_layout.*
import kotlinx.android.synthetic.main.camera_capture_layout.*
import kotlinx.android.synthetic.main.question_photo.*


open class BaseQuestionFragment : Fragment(), SensorEventListener {
    var bubble: View? = null
    var flashState = false

    var claimedTask: Task? = LiveShopperAPI.claimedTask
    var currentQuestion: Question? = LiveShopperAPI.currentQuestion

    private val sensorManager: SensorManager by lazy { activity?.getSystemService(SENSOR_SERVICE) as SensorManager }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.liveshopper.R.layout.base_question_layout, container, false)
    }

    fun addQuestionLayoutToContainer(layoutId: Int): View {
        val view = layoutInflater.inflate(layoutId, null, false)
        questionLayoutContainer.addView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateView()
    }

    open fun populateView() {
        currentQuestion?.let {
            val title = view?.findViewById(com.liveshopper.R.id.questionTitle) as TextView
            title.text = it.question
        }

        // Overlay view for questions that require photos
        currentQuestion?.photoCaptureOptions?.let {
            if (it.mustProvidePhoto) {

                activity?.findViewById<ViewGroup>(com.liveshopper.R.id.photoContainer)?.visibility = View.VISIBLE
                // Show the camera container view
                //photoContainer.visibility = View.VISIBLE

                openCameraButton.setOnClickListener { _ ->
                    showCamera(it)
                }
            }
        }
    }

    private fun showCamera(options: PhotoCaptureOptions) {
        camera_capture_layout.let { layout ->
            val previewContainer = layout.findViewById(com.liveshopper.R.id.previewContainer) as FrameLayout
            // Hide the question container.
            questionContainer.visibility = View.GONE

            // Set visibility on the camera view.
            layout.visibility = View.VISIBLE
            (activity as TaskActivity)?.supportActionBar?.hide()
            view?.background = activity?.getDrawable(com.liveshopper.R.color.black)

            val camPrev = CameraPreview(context!!, camera!!)
            previewContainer.addView(camPrev)

            bubble = layout.findViewById(com.liveshopper.R.id.bubble) as ImageView

            setPhotoOverlay(options.photoOverlayType)
            setPhotoLevel(options.photoLevelType)

            val button = layout.findViewById(com.liveshopper.R.id.button_capture) as Button
            button.setOnClickListener {
                // Capture the photo.
                camera?.takePicture(null, picture, picture)

                // Hide the layout and show the question container again.
                layout.visibility = View.GONE
                questionContainer.visibility = View.VISIBLE
                view?.background = activity?.getDrawable(com.liveshopper.R.color.white)
                (activity as TaskActivity).supportActionBar?.show()
            }

            // Flash options
            if (options.allowFlashToggle) {
                flash_toggle.setOnClickListener {
                    // Toggle the state on click
                    flashState = !flashState
                    setFlashState(camPrev)
                }
            }

            flashState = options.defaultFlashState
            setFlashState(camPrev)

            // Register to start getting accelerometer updates
            if (currentQuestion?.photoCaptureOptions?.photoLevelType != "none") {
                sensorManager.registerListener(
                    this,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }
        }
    }

    private fun setFlashState(cameraPreview: CameraPreview) {
        if (flashState) {
            flash_toggle?.background = activity?.getDrawable(com.liveshopper.R.drawable.flash_on)
            cameraPreview.enableFlash()
        } else {
            flash_toggle?.background = activity?.getDrawable(com.liveshopper.R.drawable.flash_off)
            cameraPreview.disableFlash()
        }
    }

    private fun setPhotoLevel(photoLevelType: String) {
        when (photoLevelType) {
            "vertical" -> {
                level_top.visibility = View.VISIBLE
                bubble?.visibility = View.VISIBLE
            }
            "flat" -> {
                level_flat.visibility = View.VISIBLE
                bubble?.visibility = View.VISIBLE
            }
        }
    }

    private fun setPhotoOverlay(photoOverlayType: String) {
        when (photoOverlayType) {
            "circle" -> {
                cameraOverlayContainer.setImageDrawable(activity?.getDrawable(com.liveshopper.R.drawable.overlay_circle))
            }
            "square" -> {
                cameraOverlayContainer.background = activity?.getDrawable(com.liveshopper.R.drawable.overlay_square)
            }
            "rectangle_horizontal" -> {
                cameraOverlayContainer.background =
                    activity?.getDrawable(com.liveshopper.R.drawable.overlay_rectangle_horizontal)
            }
            "rectangle_vertical" -> {
                cameraOverlayContainer.background =
                    activity?.getDrawable(com.liveshopper.R.drawable.overlay_rectangle_vertical)
            }
            "circle_square" -> {
                cameraOverlayContainer.background =
                    activity?.getDrawable(com.liveshopper.R.drawable.overlay_circle_square)
            }
        }
    }

    fun setNextButtonClickListener(function: () -> Unit) {
        view?.findViewById<Button>(com.liveshopper.R.id.nextButton)?.setOnClickListener { function() }
    }

    fun submitAnswer(taskResponse: TaskResponse) {
        LiveShopperAPI.submitAnswer(taskResponse) {
            if (it.state == STATE_COMPLETE) {
                handleRewardClaim()
            } else {
                // Still more questions to complete
                it.nextKey?.let { key -> showNextQuestionByNextKey(key) }
            }
        }
    }

    fun showNextQuestionByNextKey(nextQuestionId: String) {
        LiveShopperAPI.getNextQuestion(nextQuestionId)?.let {
            showQuestion(it)
        } ?: handleRewardClaim()
    }

    private fun handleRewardClaim() {
        // Launch the overview fragment.
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(android.R.id.content, TaskRewardFragment())
            ?.commit()
    }

    private fun showQuestion(currentQuestion: Question) {
        when (currentQuestion.type) {
            SINGLE_ANSWER_QUESTION -> launchFragment(SingleAnswerFragment())
            MULTIPLE_ANSWERS_QUESTION -> launchFragment(MultipleAnswersFragment())
            NUMERIC_OPEN_ENDED -> launchFragment(NumericOpenEndedFragment())
            OPEN_ENDED_QUESTION -> launchFragment(OpenEndedQuestionFragment())
            RATING_SCALE_QUESTION -> launchFragment(RatingScaleFragment())
            RATING_SCALE_TEXT_QUESTION -> launchFragment(RatingScaleFragment())
            DATE_QUESTION -> launchFragment(DateQuestionFragment())
            BARCODE_SCAN -> launchFragment(BarcodeQuestionFragment())
            TIME_QUESTION -> launchFragment(TimeQuestionFragment())
        }
    }

    open fun launchFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(android.R.id.content, fragment)
            ?.commit()
    }

    private fun updateBubbleLevelPosition(event: SensorEvent) {
        val k = .1f
        val x = event.values[0]
        val y = event.values[1]

        bubble?.let {
            val newX = previewContainer.measuredWidth / 2 * x * k
            val newY = previewContainer.measuredHeight / 2 * y * -k

            it.animate().translationX(newX).withLayer()
            it.animate().translationY(newY).withLayer()

            canUserTakePhoto()
        }
    }

    /**
     * Checks whether or not the camera level is within the correct range and enables the take photo button
     */
    fun canUserTakePhoto() {

        var levelContainer: View? = null
        when (currentQuestion?.photoCaptureOptions?.photoLevelType) {
            "flat" -> {
                levelContainer = level_flat
            }
            "vertical" -> {
                levelContainer = level_top
            }
        }

        levelContainer?.let {
            val firstPosition = IntArray(2)
            val secondPosition = IntArray(2)

            bubble?.getLocationOnScreen(firstPosition)
            levelContainer.getLocationOnScreen(secondPosition)

            // Rect constructor parameters: left, top, right, bottom
            val rectFirstView = Rect(
                firstPosition[0], firstPosition[1],
                firstPosition[0] + bubble?.measuredWidth!!, firstPosition[1] + bubble?.measuredHeight!!
            )
            val rectSecondView = Rect(
                secondPosition[0], secondPosition[1],
                secondPosition[0] + levelContainer.measuredWidth, secondPosition[1] + levelContainer.measuredHeight
            )

            button_capture?.let {
                it.isEnabled = rectFirstView.intersect(rectSecondView)
                if (it.isEnabled) it.alpha = 1f else it.alpha = .3f
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_ACCELEROMETER) updateBubbleLevelPosition(it)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    /** A safe way to get an instance of the Camera object. */
    private val camera by lazy {
        try {
            Camera.open() // attempt to get a Camera instance
        } catch (e: Exception) {
            // Camera is not available (in use or does not exist)
            null // returns null if camera is unavailable
        }
    }

    /** Callback for when the user snaps a photo. */
    private val picture = Camera.PictureCallback { data, _ ->
        data?.let {

            val scaledImage = LiveShopperAPI.setImageAndScale(data)
            imagePreview.background = BitmapDrawable(scaledImage)
        }
    }

}
