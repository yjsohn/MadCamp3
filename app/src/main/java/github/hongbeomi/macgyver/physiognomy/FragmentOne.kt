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
import kotlin.properties.Delegates

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
        var score = 50

        if(tmp<1.28 && tmp >1.12)       {
                one_txt1.setText("1 : 1.2 : 1")
                score+=25
                face1.setImageResource(R.drawable.good)
//                    "왼쪽 눈 : 미간 : 오른쪽 눈 황금 비율은 \n 1 : 1.2 : 1,\n 당신의 오른쪽 눈 : 미간 : 왼쪽 눈 비율은 \n" +
//                        "1 : 1.2 : 1 \n Perfect! 부러워요")
            }else{
                one_txt1.setText("1 : "+ Math.round(tmp*100)/100.0 + " : " +
                        Math.round((feature.leftEyeLength/feature.rightEyeLength)*100)/100.0)
            face1.setImageResource(R.drawable.bad)
//                    "왼쪽 눈 : 미간 : 오른쪽 눈 황금 비율은 \n 1 : 1.2 : 1,\n 당신의 오른쪽 눈 : 미간 : 왼쪽 눈 비율은 \n"
//                        + "1 : "+ Math.round(tmp*100)/100.0 + " : " +
//                        Math.round((feature.leftEyeLength/feature.rightEyeLength)*100)/100.0 +" \n 아쉽네요! 다시 찍어보면 결과가 다를지도?!")
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
                one_txt2.setText("1 : 1 : 1")
                face2.setImageResource(R.drawable.good2)
                score+=25
//                    "이마 : 코 : 하관 황금비율은 \n 1 : 1 : 1 \n 당신의 이마 : 코 : 하관 비율은 1 : 1 : 1 \n 부럽군요~")
            }else{
                one_txt2.setText("1 : "+ Math.round(tmp2*100)/100.0 + " : " + Math.round(tmp3*100)/100.0)
                face2.setImageResource(R.drawable.bad2)
//                    "이마 : 코 : 하관 황금비율은 \n 1 : 1 : 1 \n 당신의 이마 : 코 : 하관 비율은 \n 1 : "+
//                        Math.round(tmp2*100)/100.0+ " : " + Math.round(tmp3*100)/100.0 + " \n 정~말 아깝게 황금비율을 비껴갔습니다!\n" +
//                        "이만 말을 줄이겠습니다. 행복한 하루 보내세요~")
            }
        if(score==50){
            score_img.setImageResource(R.drawable.fifty)
        }else if(score==75){
            score_img.setImageResource(R.drawable.seventyfive)
        }else{
            score_img.setImageResource(R.drawable.hundred)
        }

    }
}


