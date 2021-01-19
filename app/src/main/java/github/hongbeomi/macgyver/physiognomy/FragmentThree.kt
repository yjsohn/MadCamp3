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
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_three.*


class FragmentThree : Fragment() {
    lateinit var bundle: Bundle
    val FEMALE = 1
    val MALE = 2
    val male_avg = 0.815
    val female_avg = 0.851

    var sex = FEMALE

    val WIDE = 3
    val NARROW = 4
    val SHORT = 5
    val LONG = 6

    var type1 = WIDE
    var type2 = SHORT
    var tmp : Float = 0F
    var tmp2 : Float = 0F


    var txt1 = arrayOf("폭이 넓은 코 입니다.", "폭이 좁은 코 입니다.", "길이가 긴 코 입니다.", "길이가 짧은 코 입니다." )

    var txt2 = arrayOf("폭이 넓은 코 관상은 돈 씀씀이가 많기 때문에 생활력이 왕성하다고 합니다. 이는 생활력이 있기 때문에 돈하고 인연이 많다는 뜻입니다. 강한 생활력과 좋은 처세 덕분에 스스로 운을 개척해 만년운이 좋습니다.",
        "정면에서 보면 폭이 좁고, 콧구명이 보이지 않으며, 곳곳하게 서 있어 모양과 모습은 미인형 코라고 합니다. 폭이 좁은 코를 가진 사람은 예술가로서의 재능이 있습니다.",
        "긴 코 관상은 자존심이 강하고 품위를 지키려고 하기 때문에 깔끔한 편이며 자기가 할 일을 찾아 착실하게 향상해 가는 스타일로 장수와 부귀가 동시에 누리는 관상입니다.",
        "짧은 코 관상은 성격이 싹싹하고 융통성이 있으면 인정이 많고 결단력도 빠른 편입니다. 하지만 치밀하고 심각하게 생각하는 면이 부족해 성질이 급한편이 많습니다."
    )

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

        tmp = feature.noseWidth / feature.noseLength
        tmp2 = feature.middle/(feature.upper+feature.middle+feature.lower)
        //four_txt1.setText(tmp.toString())


        four_txt1.setText("hhh")


        gender.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio_langange: RadioButton = view.findViewById(checkedId)
                invisible1.visibility = View.VISIBLE
                invisible2.visibility = View.VISIBLE
                if(radio_langange.text == "Female"){
                    if(tmp > female_avg){//폭넓
                        if(tmp2>0.33){      //길다
                            four_txt1.setText(txt1[0])
                            four_txt2.setText(txt2[0])
                            four_txt3.setText(txt1[2])
                            four_txt4.setText(txt2[2])
                            nose_hor.setImageResource(R.drawable.thick_nose)
                            nose_ver.setImageResource(R.drawable.long_nose)
                        }else {                //짧다
                            four_txt1.setText(txt1[0])
                            four_txt2.setText(txt2[0])
                            four_txt3.setText(txt1[3])
                            four_txt4.setText(txt2[3])
                            nose_hor.setImageResource(R.drawable.thick_nose)
                            nose_ver.setImageResource(R.drawable.short_nose)
                        }

                    }else{//폭좁
                        if(tmp2>0.33){//길다
                            four_txt1.setText(txt1[1])
                            four_txt2.setText(txt2[1])
                            four_txt3.setText(txt1[2])
                            four_txt4.setText(txt2[2])
                            nose_hor.setImageResource(R.drawable.thin_nose)
                            nose_ver.setImageResource(R.drawable.long_nose)
                        }else{//짧다
                            four_txt1.setText(txt1[1])
                            four_txt2.setText(txt2[1])
                            four_txt3.setText(txt1[3])
                            four_txt4.setText(txt2[3])
                            nose_hor.setImageResource(R.drawable.thin_nose)
                            nose_ver.setImageResource(R.drawable.short_nose)
                        }
                    }

                }else {
                    if(tmp > male_avg){//남자 폭 넓
                        if(tmp2<0.33){  //짧다
                            four_txt1.setText(txt1[0])
                            four_txt2.setText(txt2[0])
                            four_txt3.setText(txt1[3])
                            four_txt4.setText(txt2[3])
                            nose_hor.setImageResource(R.drawable.thick_nose)
                            nose_ver.setImageResource(R.drawable.short_nose)
                        }else { //폭넓고 길다
                            four_txt1.setText(txt1[0])
                            four_txt2.setText(txt2[0])
                            four_txt3.setText(txt1[2])
                            four_txt4.setText(txt2[2])
                            nose_hor.setImageResource(R.drawable.thick_nose)
                            nose_ver.setImageResource(R.drawable.long_nose)
                        }

                    }else{
                        if(tmp2>0.33){//폭좁고 길다
                            four_txt1.setText(txt1[1])
                            four_txt2.setText(txt2[1])
                            four_txt3.setText(txt1[2])
                            four_txt4.setText(txt2[2])
                            nose_hor.setImageResource(R.drawable.thin_nose)
                            nose_ver.setImageResource(R.drawable.long_nose)
                        }else{ //폭졻고 짧다
                            four_txt1.setText(txt1[1])
                            four_txt2.setText(txt2[1])
                            four_txt3.setText(txt1[3])
                            four_txt4.setText(txt2[3])
                            nose_hor.setImageResource(R.drawable.thin_nose)
                            nose_ver.setImageResource(R.drawable.short_nose)
                        }
                    }
                }
            }
        )
    }

    fun setText(){
        if(type1 == WIDE){
            four_txt1.setText(txt1[0])
            four_txt2.setText(txt2[0])
        }
        else{
            four_txt1.setText(txt1[1])
            four_txt2.setText(txt2[1])
        }

        if(type2 == LONG){
            four_txt3.setText(txt1[2])
            four_txt4.setText(txt2[2])
        }
        else{
            four_txt3.setText(txt1[3])
            four_txt4.setText(txt2[3])
        }
    }

    fun setType(){
        if(sex == FEMALE){
            if(tmp > female_avg)    //폭넓은
                type1 = WIDE
            else
                type1 = NARROW
        }
        else{
            if(tmp > male_avg)    //폭넓은
                type1 = WIDE
            else
                type1 = NARROW

        }

        if(tmp2 > 0.33)
            type2 = LONG
        else
            type2 = SHORT
    }
}