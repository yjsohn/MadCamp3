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
    private val fragmentThree by lazy { FragmentThree() }
    private val fragmentFour by lazy { FragmentFourth() }
    private val fragmentFifth by lazy { FragmentFifth() }
    lateinit var feature : Features


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physiognomy)
        var a = intent.getStringExtra("src")
        var top = intent.getIntExtra("TOP", 0)
        img.setImageURI(a.toUri())
        feature = intent.getSerializableExtra("FEATURE") as Features


        //비율 계산
        feature = feature.calcFeature()


        var arr1 = floatArrayOf(feature.leftEyeLength, feature.btw, feature.rightEyeLength, feature.upper, feature.middle, feature.lower)
        var arr4 = floatArrayOf(feature.noseLength, feature.noseWidth)
        Log.d("arr4444444", feature.noseLength.toString()+"          "+ feature.noseWidth.toString())
        val bundle = Bundle()
        bundle.putFloatArray("arr1", arr1)
        val myObj1 = fragmentOne
        myObj1.arguments = bundle

        val bundle4 = Bundle()
        bundle.putFloatArray("arr4", arr4)
        val myObj4 = fragmentFour
        myObj4.arguments = bundle

        initNavigationBar()
    }

    private fun initNavigationBar() {
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.first -> {
                        changeFragment(fragmentOne)
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
        var bundle : Bundle = Bundle()
        bundle.putSerializable("FEATURE", feature)
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}