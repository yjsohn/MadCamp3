package github.hongbeomi.macgyver.physiognomy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.fragment_five.*
import java.lang.Math.pow
import java.lang.Math.round

class FragmentFourth : Fragment() {  //입
    lateinit var bundle : Bundle
    val ORAL_ANGLE = 1
    val ROUND = 2
    val THICK = 3
    val SMALL = 4
    val BIG = 5
    val THIN = 6
    var style = THIN

    lateinit var entertainer : Array<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = this!!.arguments!!
        var texts : ArrayList<TextView> = ArrayList<TextView>()
        texts.add(simLip1); texts.add(simLip2); texts.add(simLip3)
        var feature: Features = bundle.getSerializable("FEATURE") as Features

        var upper_left = feature.upper_lip_bottom[1].second - feature.upper_lip_bottom[0].second
        var upper_right = feature.upper_lip_bottom[7].second - feature.upper_lip_bottom[8].second
        var upper = upper_left + upper_right

        var lower_left = feature.lower_lip_top[7].second - feature.lower_lip_top[8].second
        var lower_right = feature.lower_lip_top[1].second - feature.lower_lip_top[0].second
        var lower = lower_left + lower_right

        var width_prop = round(feature.lipLength / feature.faceWidth * 1000) / 10.0
        var thickness_prop = round(feature.lipThickness / feature.faceWidth * 1000) / 10.0
        entertainer = arrayOf(arrayOf("서강준", "김도연"), arrayOf("임시완", "오마이걸 유아"), arrayOf("강소라", "김혜수"),
            arrayOf("에이핑크 보미", "소유"), arrayOf("혜리"), arrayOf("정유미", "크리스탈", "강동원"))

        style = if(width_prop <= 27)
            SMALL
        else if((round(feature.lipLength / feature.lipThickness * 10) / 10.0) <= 2.4)
            ROUND
        else if(upper + lower >= 3)
            ORAL_ANGLE
        else if(thickness_prop >= 11.1)
            THICK
        else if(width_prop >= 30)
            BIG
        else
            THIN

        when(style){
            ORAL_ANGLE -> {
                result_lip.setText("당신은 입꼬리가 올라간 입술을 가지고 있는 사람입니다.\n" +
                        "올라간 입꼬리를 가지고 있는 사람들은 그 모양에서 느낄 수 있는 것처럼, 긍정적이면서 쾌활한 성격의 소유자입니다. " +
                        "대부분 대인관계가 좋은 편이며 아무래도 평소 미소를 잘 띠는 성격일수록 입매가 올라가기 때문에 쾌활한 성격을 가지고 있다고 볼 수 있습니다.")
                //simLip1, 2, 3 어떻게 구분해서 넣지
            }
            SMALL -> {
                result_lip.setText("당신은 작은 입을 가진 사람입니다.\n" +
                        "인형처럼 전체적으로 입술의 크기가 작고, 동그랗게 모여 있는 입술은 순수하고 솔직한 성격을 나타냅니다. " +
                        "이런 입술을 갖고 있는 사람들은 창의적으로 똑똑한 편이어서 자신이 해야 할 일을 정확하게 파악하고, " +
                        "올바른 판단을 내릴 수 있는 능력을 가진 야무진 타입입니다.")
            }
            ROUND -> {
                result_lip.setText("당신은 둥근 입술을 가진 사람입니다.\n" +
                        "체리나 사과처럼 입술의 전체적인 모양이 원에 가까운 입술은, 자기주장이 뚜렷하고 고집이 센 편입니다. " +
                        "자신만의 주관이 있어 의견을 쉽게 굽히진 않지만, 그렇다고 다른 사람의 말을 쉽게 무시하거나 비난하지 않습니다. " +
                        "그런 점에서 자신의 주관과 상관없이 타인의 말을 잘 들어주는 편입니다.")
            }
            THICK -> {
                result_lip.setText(
                    "당신은 도톰한 입술을 가진 사람으로 정도 많고 애정도 깊습니다.\n" +
                            "생활력이 강하고 활발하며 금전운과 건강운이 모두 좋은 입술 관상입니다. " +
                            "타인에 대한 정이나 이해심은 크지만, 부드럽기 때문에 결단력이 부족한 면은 있습니다."
                )
            }
            BIG -> {
                result_lip.setText("당신은 전체적으로 큰 입술을 가진 사람입니다.\n" +
                        "웃을 때도 입매가 시원하게 드러나는 입술은 커뮤니케이션 능력이 좋은 편입니다. " +
                        "따라서 사람들에게 인기가 좋고, 일에 대한 욕심이 강하고 능력도 뛰어나 성공할 가능성이 높습니다.")
            }
            THIN -> {
                result_lip.setText("당신의 입술은 얇은 입술입니다.\n" +
                        "입술의 위아래가 전반적으로 모두 얇은 사람은 자기 일에 있어 야망을 갖고 있고, 꿈을 이루기 위해 " +
                        "어떤 시련도 이겨내는 강한 정신력을 가진 사람입니다. " +
                        "얄쌍한 입술과는 달리 강하고 굳건한 편이라 할 수 있습니다. " +
                        "하지만 자신의 꿈에 너무 몰입하다 보니 다소 냉담하고 이기적으로 보이기도 합니다.")
            }
        }

        for(i : Int in entertainer[style - 1].indices) {
            texts[i].setText("#" + entertainer[style - 1][i])
            texts[i].visibility = View.VISIBLE
        }
    }
}