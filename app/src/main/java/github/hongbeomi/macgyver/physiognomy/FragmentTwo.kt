package github.hongbeomi.macgyver.physiognomy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.fragment_five.*
import kotlinx.android.synthetic.main.fragment_two.*

class FragmentTwo : Fragment() {  //눈썹
    lateinit var bundle : Bundle
    val TIGER = 1
    val CRANE = 2
    val COW = 3
    val PHOENIX = 4
    val ETC = 5

    lateinit var entertainer : Array<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = this!!.arguments!!
        var feature: Features = bundle.getSerializable("FEATURE") as Features

        entertainer = arrayOf(arrayOf("강동원", "오마이걸 지호"), arrayOf("수지", "수애"), arrayOf("김유정", "박보영"),
            arrayOf("소지섭", "유승호"), arrayOf(""))
        var texts : ArrayList<TextView> = ArrayList<TextView>()
        texts.add(simEye1); texts.add(simEye2);

        result_eye.setText("ReyeLength: " + feature.rightEyeLength + ", ReyeHeight: " + feature.rightEyeHeight
        + "\nFwidth: " + feature.faceWidth)

        var rightEyeWprop = (feature.rightEyeLength / feature.faceWidth) * 100
        var rightEyeHprop = (feature.rightEyeHeight / feature.faceWidth) * 100
        var leftEyeWprop = (feature.leftEyeLength / feature.faceWidth) * 100
        var leftEyeHprop = (feature.leftEyeHeight / feature.faceWidth) * 100

        var dif_y = (feature.dif_y/feature.faceWidth)*100
        var h = (feature.leftEyeHeight/feature.faceWidth)*100
        var w = (feature.leftEyeLength/feature.faceWidth)*100
//three_txt1.setText(dif_y.toString()+ "    "+ h+   "     "+w)
//범눈

        var type = ETC
        if(dif_y >= 3.0 && h>6.8)
            type = TIGER
        else if(dif_y<3.0 && h<7)
            type = CRANE
        else if((rightEyeWprop >= 20 && rightEyeHprop >= 7.0) || (leftEyeWprop >= 20 && leftEyeHprop >= 7.0))
            type = COW
        else if((rightEyeWprop >= 20 && rightEyeHprop <= 6.3) || (leftEyeWprop >= 20 && leftEyeHprop <= 6.3))
            type = PHOENIX
        else
            type = ETC

        when(type){
            TIGER -> result_eye.setText("당신의 눈은 범눈입니다.\n" +
                    "범눈은 눈이 큼직하며 동글고 부리부리한 눈을 말합니다. " +
                    "강직한 성격과 정의감에 불타는 성격의 소유자로서, 사회적으로 남을 돕거나 봉사를 하는 선행을 베푸는 사람들이 많고, " +
                    "부귀와 영화를 누리는 상이라 볼 수 있습니다.")
            CRANE -> result_eye.setText("당신의 눈은 학눈입니다.\n" +
                    "학눈은 적당한 크기에 눈동자가 흰 눈동자와 검은 눈동자가 분명한 눈을 말합니다. " +
                    "청렴결백하여 이상이 높고 성격이 원만하며, 주변에 신뢰가 두텁고 재복운이 좋은 상이라 볼 수 있습니다.")
            COW -> result_eye.setText("당신은 소눈을 가졌습니다.\n" +
                    "소눈을 가진 사람은 인내심이 강하며 자애로운 성격을 띕니다. " +
                    "또한 매사에 실수가 적고 부지런하여 큰 부자가 많습니다. " +
                    "이러한 눈을 가지면 수명이 길어 일생을 편안히 누립니다.")
            PHOENIX -> result_eye.setText("당신은 봉황눈을 가졌습니다!\n" +
                    "봉황눈은 길상 중의 최고로 봉황안의 소유자는 전혀 불가능할 것 같은 일도 해내는 사람입니다. " +
                    "대게 고귀한 인품을 지니고 있으며 지혜가 출중하고 덕망을 겸비했습니다. " +
                    "속으로는 원대한 포부를 가지고 있고 대인관계가 원만하고 포용력이 있으며 항상 윗자리에 군림합니다. " +
                    "대부분 정치를 잘 한 성군은 거의 용눈 또는 봉황눈을 지녔다 하였습니다.")
            ETC -> result_eye.setText("이외의 관상은 추가 예정입니다. 개발자에게 응원의 커피 기프티콘을 보내주세요!")
        }
        if(type != ETC)
            similarEye.visibility = View.VISIBLE

        for(i : Int in entertainer[type - 1].indices) {
            if(entertainer[type - 1][i].equals(""))
                break
            texts[i].setText("#" + entertainer[type - 1][i])
            texts[i].visibility = View.VISIBLE
        }

    }
}