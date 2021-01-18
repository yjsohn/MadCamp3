package github.hongbeomi.macgyver.physiognomy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {
    lateinit var bundle : Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = this!!.arguments!!
        var feature : Features = bundle.getSerializable("FEATURE") as Features
        var tmp = feature.btw / feature.leftEyeLength

        if(tmp<1.28 && tmp >1.12)       {
                one_txt1.setText("당신의 오른쪽 눈 : 미간 : 왼쪽 눈 비율은 황금비율인 1 : 1.2 : 1 입니다! 이번생은 재밌겠군요!")
            }else{
                one_txt1.setText( "이상적인 오른쪽 눈 : 미간 : 왼쪽 눈 황금 비율은 1:1.2:1이지만,xx 당신의 오른쪽 눈 : 미간 : 왼쪽 눈 비율은 "
                        + "1 : "+ Math.round(tmp*100)/100.0 + " : " +
                        Math.round((feature.leftEyeLength/feature.rightEyeLength)*100)/100.0+"입니다. 다음생을 기약해 보세요!")
            }

        var tmp2 = feature.middle / feature.upper
        var tmp3 = feature.lower / feature.upper
        var gold1 = false
        var gold2 = false
        if(tmp2>0.9 && tmp2 <1.1){
                gold1=true
                if(tmp > 0.9 && tmp3 <1.1)
                    gold2=true
            }
            if(gold1==true && gold2 == true) {
                one_txt2.setText("당신의 이마 : 코 : 하관 비율은 황금비율인 1 : 1 : 1 입니다!. 부럽군요~")
            }else{
                one_txt2.setText("당신의 이마 : 코 : 하관 비율은 1 : "+
                        Math.round(tmp2*100)/100.0+ " : " + Math.round(tmp3*100)/100.0 + "으로 정~말 아깝게 황금비율을 비껴갔습니다!\n" +
                        "이만 말을 줄이겠습니다. 행복한 하루 보내세요~")
            }

    }
}


