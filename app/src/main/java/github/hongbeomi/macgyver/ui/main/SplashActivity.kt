package github.hongbeomi.macgyver.ui.main
import  android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import github.hongbeomi.macgyver.R
import java.lang.Exception
class SplashActivity : AppCompatActivity(){
    val SPLASH_TIME_OUT: Long = 2000 //3초간 보여 주고 넘어 간다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            //어떤 액티비티로 넘어 갈지 설정 - 당연히 메인액티비로 넘어 가면 된다.
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}