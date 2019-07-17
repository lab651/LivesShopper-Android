package com.liveshopper.feature.task.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lab651.liveshopper.LiveShopperAPI.populateTaskResponse
import com.lab651.liveshopper.util.BarcodeScannedListener
import com.lab651.liveshopper.util.BarcodeScanningProcessor
import com.lab651.liveshopper.util.CameraSource
import kotlinx.android.synthetic.main.live_preview_layout.*
import kotlinx.android.synthetic.main.question_barcode.*
import timber.log.Timber


class BarcodeQuestionFragment : BaseQuestionFragment(), BarcodeScannedListener {

    override fun onScanned(barcode: String) {
        Timber.d("Barcode: $barcode")
        barcodeNumber.text = barcode
        scannerContainer.visibility = View.GONE
        mainContent.visibility = View.VISIBLE
        firePreview.stop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.liveshopper.R.layout.question_barcode, container, false)
    }

    override fun populateView() {

        scanBarcodeButton.setOnClickListener {
            showBarcodeScanner()
        }

        nextButton.setOnClickListener {
            if (barcodeNumber.text.isNullOrEmpty()) {
                Toast.makeText(context, "Scan a barcode before continuing", Toast.LENGTH_LONG).show()
            } else {
                val taskResponse = populateTaskResponse()
                taskResponse.userAnswer = barcodeNumber.text.toString()
                submitAnswer(taskResponse)
            }
        }
    }

    fun showBarcodeScanner() {
        // Hide the main content and show the scanner container
        mainContent.visibility = View.INVISIBLE
        scannerContainer.visibility = View.VISIBLE

        val cameraSource = CameraSource(activity, fireFaceOverlay)
        cameraSource.setMachineLearningFrameProcessor(BarcodeScanningProcessor(this))
        firePreview?.start(cameraSource, fireFaceOverlay)
    }
}
