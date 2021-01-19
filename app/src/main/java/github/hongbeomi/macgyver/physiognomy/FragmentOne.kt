package github.hongbeomi.macgyver.physiognomy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import github.hongbeomi.macgyver.R
import kotlinx.android.synthetic.main.fragment_one.*
import android.util.Log

class FragmentOne : Fragment() {    //황금비율
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
        var width_prop = feature.lipLength / feature.faceWidth * 100
        //var thickness_prop = ((feature.upperLipThickness + feature.lowerLipThickness) / feature.faceWidth) * 100
        var thickness_prop = (feature.lipThickness / feature.faceWidth) * 100
        /*
        frag_one_tv.setText("lip_length: " + feature.lipLength + ", upper_lip_width: " + feature.upperLipThickness + ", lower_lip_width: " + feature.lowerLipThickness +
                "\nwidth_prop: " + width_prop + ", thick_prop: " + thickness_prop)

         */
        frag_one_tv.setText("lip_length: " + feature.lipLength + ", upper_lip_width: " + feature.upperLipThickness + ", lower_lip_width: " + feature.lowerLipThickness +
                "\nwidth_prop: " + width_prop + ", thick_prop: " + thickness_prop +
            "\nUP: " + feature.upper + ", Mid: " + feature.middle + ", Low: " + feature.lower + "\ntop: " + feature.top +
        "\nnoseW: " + feature.noseWidth + ", noseL: " + feature.noseLength +
        "\nlip_thickness: " + feature.lipThickness +
                "\nfaceW: " + feature.faceWidth + " eyeW: " + feature.rightEyeLength + " eyeH: " + feature.rightEyeHeight +
        "\nReyeWprop: " + (feature.rightEyeLength / feature.faceWidth) * 100 + ", ReyeHprop: " + (feature.rightEyeHeight / feature.faceWidth) * 100 +
                "\nLeyeWprop: " + (feature.leftEyeLength / feature.faceWidth) * 100 + ", LeyeHprop: " + (feature.leftEyeHeight / feature.faceWidth) * 100)
        Log.i("aaaaaaaa", feature.faceWidth.toString())
    }
}