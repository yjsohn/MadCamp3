package github.hongbeomi.macgyver.physiognomy
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.activity_physiognomy.*


class ShowPhysiognomy : AppCompatActivity() {
    private val fragmentOne by lazy { FragmentOne() }
    private val fragmentTwo by lazy { FragmentTwo() }
    private val fragmentThree by lazy { FragmentThree() }
    private val fragmentFour by lazy { FragmentFourth() }
    private val fragmentFifth by lazy { FragmentFifth() }



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
//        var txt1 = ("face_width: " + feature.faceWidth + ", lip_len: " + feature.lipLength.toString() + ", lip_upper_th: " + feature.upperLipThickness + ", lip_lower_th: " + feature.lowerLipThickness +
//                "\nproportion: " + proportion)
//        feature_tv.setText("face_width: " + feature.faceWidth + ", lip_len: " + feature.lipLength.toString() + ", lip_upper_th: " + feature.upperLipThickness + ", lip_lower_th: " + feature.lowerLipThickness +
//                "\nproportion: " + proportion)

        var txt1 = "leftEyeLength: "+ feature.leftEyeLength+
                "btw: "+ feature.btw/feature.leftEyeLength +"rightEyeLength: "+ feature.rightEyeLength/feature.leftEyeLength
        var arr1 = floatArrayOf(feature.leftEyeLength, feature.btw, feature.rightEyeLength, feature.upper, feature.middle, feature.lower)

        val bundle = Bundle()
        //bundle.putString("params", txt1)
        bundle.putFloatArray("arr1", arr1)

        val myObj = fragmentOne
        myObj.arguments = bundle

        initNavigationBar()
    }
    private fun initNavigationBar() {
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.first -> {
                        changeFragment(fragmentOne)
                    }
                    R.id.second -> {
                        changeFragment(fragmentTwo)
                    }
                    R.id.third -> {
                        changeFragment(fragmentThree)
                    }
                    R.id.fourth -> {
                        changeFragment(fragmentFour)
                    }
                    R.id.fifth -> {
                        changeFragment(fragmentFifth)
                    }
                }
                true
            }
            selectedItemId = R.id.first
        }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}