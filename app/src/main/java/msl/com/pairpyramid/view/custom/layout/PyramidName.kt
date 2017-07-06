package msl.com.pairpyramid.view.custom.layout

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.pyramid_name.view.*
import msl.com.pairpyramid.R


open class PyramidName : RelativeLayout {

    var size: Int = 50
    var name: String = ""

    constructor(context: Context, size: Int, name: String) : super(context) {
        this.size = size

        initView()
        setLayoutSize(size)
        text_name.text = name
    }


    private fun initView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(infService) as LayoutInflater
        val pyramidItem = inflater.inflate(R.layout.pyramid_name, this, false)
        addView(pyramidItem)
    }

    private fun setLayoutSize(size: Int) {
        val params = LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val scale = resources.displayMetrics.density
        val dpPixel = (size * scale).toInt()
        params.height = dpPixel
        params.width = dpPixel
        setLayoutParams(params)
    }


}