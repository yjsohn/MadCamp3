package github.hongbeomi.macgyver.physiognomy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.fragment_three.*

class FragmentThree : Fragment() {
    lateinit var bundle : Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_three, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = this!!.arguments!!
        var feature: Features = bundle.getSerializable("FEATURE") as Features

        var fw = feature.faceWidth

        var dif_y = (feature.dif_y/fw)*100
        var h = (feature.leftEyeHeight/fw)*100
        var w = (feature.leftEyeLength/fw)*100

        //three_txt1.setText(dif_y.toString()+ "    "+ h+   "     "+w)

        //범눈
        if(dif_y >= 3.0 && h>6.8){
            three_txt1.setText("당신의 눈은 범눈입니다. \n 범눈은 눈이 큼직하며 동글고 부리부리한 눈을 말합니다. 강직한 성격과 정의감에 불타는 성격의 소유자로서, 사회적으로 남을 돕거나" +
                    "봉사를 하는 선행을 베푸는 사람들이 많고, 부귀와 영화를 누리는 상이라 볼 수 있습니다.")
        }else if(dif_y<3.0 && h<7) {
            three_txt1.setText("당신의 눈은 학눈입니다. \n 학눈은 적당한 크기에 눈동자가 흰 눈동자와 검은 눈동자가 분명한 눈을 말합니다. 청렴결백하여 이상이 높고 성격이 원만하며, 주변에 신뢰가 두텁고 재복운이 좋은 상이라 볼 수 있습니다.")
        }else{
            three_txt1.setText("이외의 관상은 추가 예정입니다. 개발자에게 응원의 커피 기프티콘을 보내주세요!")
        }
    }
}