package github.hongbeomi.macgyver.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.Feature
import com.google.android.gms.vision.face.Contour
import com.google.mlkit.vision.face.FaceContour
import github.hongbeomi.macgyver.R
import github.hongbeomi.macgyver.camerax.CameraManager
import github.hongbeomi.macgyver.databinding.ActivityMainBinding
import github.hongbeomi.macgyver.mlkit.vision.face_detection.FaceContourGraphic
import github.hongbeomi.macgyver.physiognomy.Features
import github.hongbeomi.macgyver.physiognomy.ShowPhysiognomy
import github.hongbeomi.macgyver.ui.base.BaseActivity
import github.hongbeomi.macgyver.util.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Math.*

class MainActivity : BaseActivity() {
    val FACE = 1
    val LEFT_EYEBROW_TOP = 2
    val LEFT_EYEBROW_BOTTOM = 3
    val RIGHT_EYEBROW_TOP = 4
    val RIGHT_EYEBROW_BOTTOM = 5
    val LEFT_EYE = 6
    val RIGHT_EYE = 7
    val UPPER_LIP_TOP = 8
    val UPPER_LIP_BOTTOM = 9
    val LOWER_LIP_TOP = 10
    val LOWER_LIP_BOTTOM = 11
    val NOSE_BRIDGE = 12
    val NOSE_BOTTOM = 13

    private lateinit var src : String

    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val viewModel: MainViewModel by viewModel()
    private lateinit var cameraManager: CameraManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createCameraManager()
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            initViewModel()
        }
        if (allPermissionsGranted()) {
            cameraManager.startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                cameraManager.startCamera()
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            onItemSelectedEvent.observe(::getLifecycle) {
                cameraManager.changeAnalyzer(it)
            }
            onFabButtonEvent.observe(::getLifecycle) {
                it?.let {
                    binding.fabFinder.transform()
                    cameraManager.changeCameraSelector()
                }
            }
            onShutterButtonEvent.observe(::getLifecycle) {
                it?.let { takePicture() }
            }
        }
    }

    private fun createCameraManager() {
        cameraManager = CameraManager(
            this,
            binding.previewViewFinder,
            this,
            binding.graphicOverlayFinder
        )
    }

    //사진 찍었을 때
    private fun takePicture() {
        // shutter effect
        Toast.makeText(this, "take a picture!", Toast.LENGTH_SHORT).show()
        setOrientationEvent()

        cameraManager.imageCapture.takePicture(
            cameraManager.cameraExecutor,
            object : ImageCapture.OnImageCapturedCallback() {
                @SuppressLint("UnsafeExperimentalUsageError", "RestrictedApi")
                override fun onCaptureSuccess(image: ImageProxy) {
                    image.image?.let {
                        imageToBitmapSaveGallery(it)
                    }
                    super.onCaptureSuccess(image)
                }
            })

        /*
        var faceContourGraphic  = cameraManager.getGraphicOverlay().getGraphic() as FaceContourGraphic
        val contour : MutableList<FaceContour> = faceContourGraphic.getContour()
        Log.i("aaaaaaaa", contour.toString())
         */

        /*
        val result_string = contour[0].points[0].x
        val nextIntent = Intent(this, ShowPhysiognomy::class.java)
        //contour 넘기기
        //정보 정리 후 넘기기
        nextIntent.putExtra("CONTOUR", result_string)
        startActivity(nextIntent)
         */


    }

    private fun imageToBitmapSaveGallery(image: Image) {
        image.imageToBitmap()
            ?.rotateFlipImage(
                cameraManager.rotation,
                cameraManager.isFrontMode()
            )
            ?.scaleImage(
                binding.previewViewFinder,
                cameraManager.isHorizontalMode()
            )
            ?.let { bitmap ->
                binding.graphicOverlayFinder.processCanvas.drawBitmap(
                    bitmap,
                    0f,
                    bitmap.getBaseYByView(
                        binding.graphicOverlayFinder,
                        cameraManager.isHorizontalMode()
                    ),
                    Paint().apply {
                        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
                    })
                src = binding.graphicOverlayFinder.processBitmap.saveToGallery(this@MainActivity)
                val nextIntent = Intent(this, ShowPhysiognomy::class.java)
                nextIntent.putExtra("src", src)

                var faceContourGraphic  = cameraManager.getGraphicOverlay().getGraphic() as FaceContourGraphic
                val contour : MutableList<FaceContour> = faceContourGraphic.getContour()
                var top = faceContourGraphic.getBounding()?.top
                Log.i("aaaaaaaa", contour[5].toString() + "\n" + contour[6].toString())
                if(top == null)
                    top = 0
                var feature : Features = Features(top)
                for(i : Int in 0..12) {
                    var size = contour[i].points.size - 1
                    var tmp  : ArrayList<Pair<Float, Float>> = ArrayList<Pair<Float, Float>>()
                    for (j: Int in 0..size) {
                        tmp.add(Pair(contour[i].points[j].x, contour[i].points[j].y))
                    }
                    when(i + 1){
                        FACE -> feature.face = tmp
                        LEFT_EYEBROW_TOP -> feature.left_eyebrow_top= tmp
                        LEFT_EYEBROW_BOTTOM -> feature.left_eyebrow_bottom = tmp
                        RIGHT_EYEBROW_TOP -> feature.right_eyebrow_top = tmp
                        RIGHT_EYEBROW_BOTTOM -> feature.right_eyebrow_bottom = tmp
                        LEFT_EYE -> feature.left_eye = tmp
                        RIGHT_EYE -> feature.right_eye = tmp
                        UPPER_LIP_TOP -> feature.upper_lip_top = tmp
                        UPPER_LIP_BOTTOM -> feature.upper_lip_bottom = tmp
                        LOWER_LIP_TOP -> feature.lower_lip_top = tmp
                        LOWER_LIP_BOTTOM -> feature.lower_lip_bottom = tmp
                        NOSE_BRIDGE -> feature.nose_bridge = tmp
                        NOSE_BOTTOM -> feature.nose_bottom = tmp
                    }
                }
                nextIntent.putExtra("FEATURE", feature)
                startActivity(nextIntent)
            }
    }

    private fun setOrientationEvent() {
        val orientationEventListener = object : OrientationEventListener(this as Context) {
            override fun onOrientationChanged(orientation: Int) {
                val rotation: Float = when (orientation) {
                    in 45..134 -> 270f
                    in 135..224 -> 180f
                    in 225..314 -> 90f
                    else -> 0f
                }
                cameraManager.rotation = rotation
            }
        }
        orientationEventListener.enable()
    }


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}