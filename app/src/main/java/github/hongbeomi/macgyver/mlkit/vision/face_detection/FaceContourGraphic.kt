package github.hongbeomi.macgyver.mlkit.vision.face_detection

import android.graphics.*
import android.util.Log
import androidx.annotation.ColorInt
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceContour
import github.hongbeomi.macgyver.camerax.GraphicOverlay
import java.io.Serializable

class FaceContourGraphic (
    overlay: GraphicOverlay,
    private val face: Face,
    private val imageRect: Rect
) : GraphicOverlay.Graphic(overlay), Serializable {

    //마지막으로 저장된 contour를 이용

    private val facePositionPaint: Paint
    private val idPaint: Paint
    private val boxPaint: Paint

    init {
        val selectedColor = Color.RED

        facePositionPaint = Paint()
        facePositionPaint.color = Color.WHITE

        idPaint = Paint()
        idPaint.color = selectedColor
        idPaint.textSize = ID_TEXT_SIZE

        boxPaint = Paint()
        boxPaint.color = selectedColor
        boxPaint.style = Paint.Style.STROKE
        boxPaint.strokeWidth = BOX_STROKE_WIDTH
    }

    private fun Canvas.drawFace(facePosition: Int, @ColorInt selectedColor: Int) {
        val contour = face.getContour(facePosition)

        val path = Path()
        contour?.points?.forEachIndexed { index, pointF ->
            if (index == 0) {
                path.moveTo(
                    translateX(pointF.x),
                    translateY(pointF.y)
                )
            }
            path.lineTo(
                translateX(pointF.x),
                translateY(pointF.y)
            )
        }
        val paint = Paint().apply {
            color = selectedColor
            style = Paint.Style.STROKE
            strokeWidth = BOX_STROKE_WIDTH
        }
        drawPath(path, paint)
    }

    override fun draw(canvas: Canvas?) {

        val rect = calculateRect(
            imageRect.height().toFloat(),
            imageRect.width().toFloat(),
            face.boundingBox
        )
        canvas?.drawRect(rect, boxPaint)

        val contours = face.allContours

        contours.forEach {
            it.points.forEach { point ->
                val px = translateX(point.x)
                val py = translateY(point.y)
                canvas?.drawCircle(px, py, FACE_POSITION_RADIUS, facePositionPaint)
            }
        }
        //Log.d("aaaaaaaa",contours.toString())

        // face
        canvas?.drawFace(FaceContour.FACE, Color.DKGRAY)

        // left eye
        canvas?.drawFace(FaceContour.LEFT_EYEBROW_TOP, Color.GREEN)
        canvas?.drawFace(FaceContour.LEFT_EYE, Color.BLACK)
        canvas?.drawFace(FaceContour.LEFT_EYEBROW_BOTTOM, Color.GREEN)

        // right eye
        canvas?.drawFace(FaceContour.RIGHT_EYE, Color.BLACK)
        canvas?.drawFace(FaceContour.RIGHT_EYEBROW_BOTTOM, Color.GREEN)
        canvas?.drawFace(FaceContour.RIGHT_EYEBROW_TOP, Color.GREEN)

        // nose
        canvas?.drawFace(FaceContour.NOSE_BOTTOM, Color.CYAN)
        canvas?.drawFace(FaceContour.NOSE_BRIDGE, Color.CYAN)

        // lip
        canvas?.drawFace(FaceContour.LOWER_LIP_BOTTOM, Color.MAGENTA)
        canvas?.drawFace(FaceContour.LOWER_LIP_TOP, Color.MAGENTA)
        canvas?.drawFace(FaceContour.UPPER_LIP_BOTTOM, Color.MAGENTA)
        canvas?.drawFace(FaceContour.UPPER_LIP_TOP, Color.MAGENTA)
    }

    fun getContour() : MutableList<FaceContour> {
        return face.allContours
    }

    companion object {
        //값 바꿔보기
        private const val FACE_POSITION_RADIUS = 4.0f
        private const val ID_TEXT_SIZE = 30.0f
        private const val BOX_STROKE_WIDTH = 5.0f
    }
}