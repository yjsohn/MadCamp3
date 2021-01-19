package github.hongbeomi.macgyver.physiognomy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import github.hongbeomi.macgyver.util.setOnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_physiognomy.*
class ShowPhysiognomy : AppCompatActivity() {
    private val fragmentOne by lazy { FragmentOne() }
    private val fragmentTwo by lazy { FragmentTwo() }
    private val fragmentThree by lazy { FragmentThree() }
    private val fragmentFour by lazy { FragmentFourth() }
    lateinit var feature : Features

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physiognomy)
        var a = intent.getStringExtra("src")
        var top = intent.getIntExtra("TOP", 0)
        img.setImageURI(a.toUri())
        feature = intent.getSerializableExtra("FEATURE") as Features
        feature = feature.calcFeature()

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