package github.hongbeomi.macgyver.physiognomy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.activity_physiognomy.*
class ShowPhysiognomy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physiognomy)
        var a = intent.getStringExtra("src")
        var top = intent.getIntExtra("TOP", 0)
        img.setImageURI(a.toUri())
        var feature : Features = intent.getSerializableExtra("FEATURE") as Features


        //비율 계산
        feature = feature.calcFeature()
        var proportion = (feature.lipLength / feature.faceWidth) * 100
        feature_tv.setText("face_width: " + feature.faceWidth + ", lip_len: " + feature.lipLength.toString() + ", lip_upper_th: " + feature.upperLipThickness + ", lip_lower_th: " + feature.lowerLipThickness +
                "\nproportion: " + proportion)

    }
}