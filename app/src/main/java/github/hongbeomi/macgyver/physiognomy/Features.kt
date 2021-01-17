package github.hongbeomi.macgyver.physiognomy

import com.google.android.gms.vision.face.Contour
import com.google.mlkit.vision.face.FaceContour
import github.hongbeomi.macgyver.camerax.GraphicOverlay
import github.hongbeomi.macgyver.mlkit.vision.face_detection.FaceContourGraphic
import java.io.Serializable

class Features () : Serializable{
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

    lateinit var face : ArrayList<Pair<Float, Float>>
    lateinit var left_eyebrow_top : ArrayList<Pair<Float, Float>>
    lateinit var left_eyebrow_bottom : ArrayList<Pair<Float, Float>>
    lateinit var right_eyebrow_top : ArrayList<Pair<Float, Float>>
    lateinit var right_eyebrow_bottom : ArrayList<Pair<Float, Float>>
    lateinit var left_eye : ArrayList<Pair<Float, Float>>
    lateinit var right_eye : ArrayList<Pair<Float, Float>>
    lateinit var upper_lip_top : ArrayList<Pair<Float, Float>>
    lateinit var upper_lip_bottom : ArrayList<Pair<Float, Float>>
    lateinit var lower_lip_top : ArrayList<Pair<Float, Float>>
    lateinit var lower_lip_bottom : ArrayList<Pair<Float, Float>>
    lateinit var nose_bridge : ArrayList<Pair<Float, Float>>
    lateinit var nose_bottom : ArrayList<Pair<Float, Float>>


    fun calcFeature(faceContour: MutableList<FaceContour>){
        //Face
        var sizeI = faceContour.size - 1
        var sizeJ = faceContour[0].points.size - 1

        var faceWidth : Float = 0F
        var faceLength : Float = 0F
        var leftEyeLength : Float = 0F
        var leftEyeHeight : Float = 0F
        var rightEyeLength : Float = 0F
        var rightEyeHeight : Float = 0F
        var lipLength : Float = 0F
        var upperLipThickness : Float = 0F
        var lowerLipThickness : Float = 0F
        var noseLength : Float = 0F
        var noseWidth : Float = 0F
        var philtrum : Float = 0F

        var minX : Float = 0F
        var minY : Float = 0F
        var maxX : Float = 0F
        var maxY : Float = 0F

        for(i : Int in 0..sizeI) {
            //var max
            if(i < UPPER_LIP_TOP - 1) {
                for (j: Int in 0..sizeJ) {
                    if (j == 0) {
                        minX = faceContour[i].points[j].x
                        minY = faceContour[i].points[j].y
                        maxX = faceContour[i].points[j].x
                        maxY = faceContour[i].points[j].y
                    }
                    if (faceContour[i].points[j].x < minX)
                        minX = faceContour[i].points[j].x
                    if (faceContour[i].points[j].y < minY)
                        minY = faceContour[i].points[j].y
                    if (faceContour[i].points[j].x > maxX)
                        maxX = faceContour[i].points[j].x
                    if (faceContour[i].points[j].y > maxY)
                        maxY = faceContour[i].points[j].y
                }
            }
            when(i + 1){
                FACE -> {
                    faceWidth = maxX - minX
                    faceLength = maxY - minY
                }
                LEFT_EYE -> {
                    leftEyeLength =
                        Math.abs(faceContour[LEFT_EYE - 1].points[0].x - faceContour[LEFT_EYE - 1].points[8].x)
                    leftEyeHeight = maxY - minY
                }
                RIGHT_EYE -> {
                    rightEyeLength =
                        Math.abs(faceContour[RIGHT_EYE - 1].points[0].x - faceContour[RIGHT_EYE - 1].points[8].x)
                    rightEyeHeight = maxY - minY
                }
                UPPER_LIP_TOP -> lipLength = faceContour[UPPER_LIP_TOP - 1].points[10].x - faceContour[UPPER_LIP_TOP - 1].points[0].x
                UPPER_LIP_BOTTOM -> upperLipThickness = faceContour[UPPER_LIP_BOTTOM - 1].points[4].y - faceContour[UPPER_LIP_TOP - 1].points[5].y
                LOWER_LIP_BOTTOM -> lowerLipThickness = faceContour[LOWER_LIP_BOTTOM - 1].points[4].y - faceContour[LOWER_LIP_TOP - 1].points[4].y
                NOSE_BRIDGE -> {
                    var x1 = faceContour[NOSE_BRIDGE - 1].points[0].x
                    var x2 = faceContour[NOSE_BRIDGE - 1].points[1].x
                    var y1 = faceContour[NOSE_BRIDGE - 1].points[0].y
                    var y2 = faceContour[NOSE_BRIDGE - 1].points[1].y
                    noseLength = Math.sqrt(
                        Math.pow(
                            (x1 - x2).toDouble(),
                            2.0
                        ) + Math.pow((y1 - y2).toDouble(), 2.0)
                    ).toFloat()
                }
                NOSE_BOTTOM -> {
                    noseWidth =
                        faceContour[NOSE_BOTTOM - 1].points[2].x - faceContour[NOSE_BOTTOM - 1].points[0].x
                    philtrum =
                        faceContour[UPPER_LIP_TOP - 1].points[5].y - faceContour[NOSE_BOTTOM - 1].points[1].y
                }
            }
        }
    }
}