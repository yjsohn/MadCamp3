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
        img.setImageURI(a.toUri())
        var f : Features = intent.getSerializableExtra("FEATURE") as Features
        feature_tv.setText(f.nose_bridge[0].toString())

    }
}