package github.hongbeomi.macgyver.physiognomy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.fragment_four.*


class FragmentFourth : Fragment() {
    lateinit var bundle: Bundle

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
        var feature: Features = bundle.getSerializable("FEATURE") as Features

        var tmp = feature.noseWidth / feature.noseLength
        var tmp2 = feature.middle/(feature.upper+feature.middle+feature.lower)
        //four_txt1.setText(tmp.toString())
        var male_avg = 0.815
        var female_avg = 0.851

        var txt1 = arrayOf("당신은 폭이 넓고 짧은 코를가지고 있습니다.","당신은 폭이 넓고 긴 코를 가지고 있습니다.", "당신은 폭이 좁고 긴 코를 가지고 있습니다.", "당신은 폭이 좁고 짧은 코를 가지고 있습니다.")

        var txt2 = arrayOf("폭이 넓은 코 관상은 돈 씀씀이가 많기 때문에 생활력이 왕성하다고 합니다. 이는 생활력이 있기 때문에 돈하고 인연이 많다는 뜻이랍니다. 강하고 억센 생활력과 돈의 힘으로 인기가 많아 바람둥이가 될 수도 있습니다. 또한 짧은 코 관상은 성격이 싹싹하고 융통성이 있으면 인정이 많고 결단력도 빠른 편이며, 관상학적으로 다른 부분까지 재능이 있게 생겼으면 위와 같은 성격이 더욱 두드러져 지위도 높아질 수 있습니다. 하지만 치밀하고 심각하게 생각하는 면이 부족해 성질이 급한편이 많습니다.",
            "폭이 넓은 코 관상은 돈 씀씀이가 많기 때문에 생활력이 왕성하다고 합니다. 이는 생활력이 있기 때문에 처세가 좋아 스스로 운을 개척해 만년운이 좋습니다. 강하고 억센 생활력과 돈의 힘으로 인기가 많아 바람둥이가 될 수도 있습니다. 또한 긴 코 관상은 자존심이 강하고 품위를 지키려고 하기 때문에 깔끔한 편이며 자기가 할 일을 찾아 착실하게 향상해 가는 스타일로 장수와 부귀가 동시에 누리는 관상입니다.",
            "정면에서 보면 폭이 좁고, 콧구명이 보이지 않으며, 곳곳하게 서 있어 모양과 모습은 미인형 코라고 합니다. 폭이 좁은 코를 가진 사람은 예술가로서의 재능이 있습니다. 또한 긴 코 관상은 자존심이 강하고 품위를 지키려고 하기 때문에 깔끔한 편이며 자기가 할 일을 찾아 착실하게 향상해 가는 스타일로 장수와 부귀가 동시에 누리는 관상입니다.",
            "정면에서 보면 폭이 좁고, 콧구명이 보이지 않으며, 곳곳하게 서 있어 모양과 모습은 미인형 코라고 합니다. 폭이 좁은 코를 가진 사람은 예술가로서의 재능이 있습니다. 또한 짧은 코 관상은 성격이 싹싹하고 융통성이 있으면 인정이 많고 결단력도 빠른 편이며, 관상학적으로 다른 부분까지 재능이 있게 생겼으면 위와 같은 성격이 더욱 두드러져 지위도 높아질 수 있습니다. 하지만 치밀하고 심각하게 생각하는 면이 부족해 성질이 급한편이 많습니다.")

        gender.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio_langange: RadioButton = view.findViewById(checkedId)
                if(radio_langange.text == "Female"){
                        if(tmp > female_avg){
                            if(tmp2<0.33){
                                four_txt1.setText(txt1[0])
                                four_txt2.setText(txt2[0]+" 여성이라면 접객업을 하게 되면 크게 성공할 운이라고 합니다.")
                            }else {
                                four_txt1.setText(txt1[1])
                                four_txt2.setText(txt2[1])
                            }

                        }else{
                            if(tmp2>0.33){
                                four_txt1.setText(txt1[2])
                                four_txt2.setText(txt2[2])
                            }else{
                                four_txt1.setText(txt1[3])
                                four_txt2.setText(txt2[3] + " 여성이라면 접객업을 하게 되면 크게 성공할 운이라고 합니다.")
                            }
                        }

                }else {
                    if(tmp > male_avg){
                        if(tmp2<0.33){
                            four_txt1.setText(txt1[0])
                            four_txt2.setText(txt2[0])
                        }else {
                            four_txt1.setText(txt1[1])
                            four_txt2.setText(txt2[1])
                        }

                    }else{
                        if(tmp2>0.33){
                            four_txt1.setText(txt1[2])
                            four_txt2.setText(txt2[2])
                        }else{
                            four_txt1.setText(txt1[3])
                            four_txt2.setText(txt2[3])
                        }
                    }
                }
            }
        )
    }
}