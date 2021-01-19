package github.hongbeomi.macgyver.physiognomy

import com.google.android.gms.vision.face.Contour
import com.google.mlkit.vision.face.FaceContour
import github.hongbeomi.macgyver.camerax.GraphicOverlay
import github.hongbeomi.macgyver.mlkit.vision.face_detection.FaceContourGraphic
import java.io.Serializable

class Features (var top : Int) : Serializable{
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


    var faceWidth : Float = 0F
    var faceLength : Float = 0F
    var leftEyeLength : Float = 0F
    var leftEyeHeight : Float = 0F
    var rightEyeLength : Float = 0F
    var rightEyeHeight : Float = 0F
    var lipLength : Float = 0F
    var upperLipThickness : Float = 0F
    var lowerLipThickness : Float = 0F
    var lipThickness : Float = 0F
    var noseLength : Float = 0F
    var noseWidth : Float = 0F
    var philtrum : Float = 0F
    var middle_forehead = 0F
    var upper = 0F
    var middle = 0F
    var lower = 0F
    var dif_y = 0F

    val totalList : MutableList<MutableList<Pair<Float, Float>>> = ArrayList()

    fun calcFeature() : Features{
        totalList.add(face)
        totalList.add(left_eyebrow_top)
        totalList.add(left_eyebrow_bottom)
        totalList.add(right_eyebrow_top)
        totalList.add(right_eyebrow_bottom)
        totalList.add(left_eye)
        totalList.add(right_eye)
        totalList.add(upper_lip_top)
        totalList.add(upper_lip_bottom)
        totalList.add(lower_lip_top)
        totalList.add(lower_lip_bottom)
        totalList.add(nose_bridge)
        totalList.add(nose_bottom)

        //Face
        var sizeI = totalList.size - 1

        var minX : Float = 0F
        var minY : Float = 0F
        var maxX : Float = 0F
        var maxY : Float = 0F

        for(i : Int in 0..sizeI) {
            //var max
            var sizeJ = totalList[i].size - 1
            for (j: Int in 0..sizeJ) {
                if (j == 0) {
                    minX = totalList[i][j].first
                    minY = totalList[i][j].second
                    maxX = totalList[i][j].first
                    maxY = totalList[i][j].second
                }
                else {
                    if (totalList[i][j].first < minX)
                        minX = totalList[i][j].first
                    if (totalList[i][j].second < minY)
                        minY = totalList[i][j].second
                    if (totalList[i][j].first > maxX)
                        maxX = totalList[i][j].first
                    if (totalList[i][j].second > maxY)
                        maxY = totalList[i][j].second
                }
            }
            when (i + 1) {
                FACE -> {
                    //faceWidth = maxX - minX
                    faceWidth = totalList[i][8].first - totalList[i][28].first
                    faceLength = maxY - top
                }
                LEFT_EYE -> {
                    leftEyeLength =
                        Math.abs(totalList[i][0].first - totalList[i][8].first)
                    leftEyeHeight = maxY - minY
                    dif_y = Math.abs(totalList[i][0].second - totalList[i][8].second)
                }
                RIGHT_EYE -> {
                    rightEyeLength =
                        Math.abs(totalList[i][0].first - totalList[i][8].first)
                    rightEyeHeight = maxY - minY
                }
                //UPPER_LIP_TOP -> lipLength = totalList[i][10].first - totalList[i][0].first
                UPPER_LIP_BOTTOM -> {
                    upperLipThickness = totalList[UPPER_LIP_BOTTOM - 1][4].second - totalList[UPPER_LIP_TOP - 1][5].second
                    lipLength = totalList[i][8].first - totalList[i][0].first
                }
                //LOWER_LIP_TOP -> lipLength = totalList[i][0].first - totalList[i][8].first
                LOWER_LIP_BOTTOM -> {
                    lowerLipThickness = totalList[LOWER_LIP_BOTTOM - 1][4].second - totalList[LOWER_LIP_TOP - 1][4].second
                    lipThickness = totalList[LOWER_LIP_BOTTOM - 1][4].second - totalList[UPPER_LIP_TOP - 1][5].second
                }
                NOSE_BRIDGE -> {
                    var x1 = totalList[i][0].first
                    var x2 = totalList[i][1].first
                    var y1 = totalList[i][0].second
                    var y2 = totalList[i][1].second
                    noseLength = Math.sqrt(
                        Math.pow(
                            (x1 - x2).toDouble(),
                            2.0
                        ) + Math.pow((y1 - y2).toDouble(), 2.0)
                    ).toFloat()
                }
                NOSE_BOTTOM -> {
                    noseWidth =
                        totalList[i][2].first - totalList[i][0].first
                    philtrum =
                        totalList[UPPER_LIP_TOP - 1][5].second - totalList[NOSE_BOTTOM - 1][1].second
                }
            }
        }
        middle_forehead = totalList[RIGHT_EYE - 1][0].first - totalList[RIGHT_EYE - 1][8].first
        //var std = totalList[LEFT_EYEBROW_TOP - 1][4].second + totalList[LEFT_EYEBROW_TOP - 1][4].second
        upper = totalList[LEFT_EYEBROW_BOTTOM - 1][4].second - top
        middle = totalList[NOSE_BOTTOM - 1][1].second - totalList[LEFT_EYEBROW_BOTTOM - 1][4].second
        lower = totalList[FACE - 1][18].second - totalList[NOSE_BOTTOM - 1][1].second

        return this
    }
}