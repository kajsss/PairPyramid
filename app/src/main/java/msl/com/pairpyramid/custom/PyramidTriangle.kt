package msl.com.pairpyramid.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.pyramid_item.view.*
import msl.com.pairpyramid.R


open class PyramidTriangle : RelativeLayout {

    var size : Int = 50
    var fontSize : Int = 15
    var point : Int ?= null

    constructor(context : Context , size : Int) : super(context) {
        this.size = size
        initView()
        setLayoutSize(size)
    }

    constructor(context : Context , size : Int, fontSize : Int , point : Int) : super(context) {
        this.size = size
        this.fontSize = fontSize
        initView()
        setPoint(point)
        setLayoutSize(size)
    }


    constructor(context : Context , attrSet : AttributeSet, size : Int) : super(context, attrSet) {
        this.size = size
        initView()
        setLayoutSize(size)
    }

    private fun initView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(infService) as LayoutInflater
        val pyramidItem = inflater.inflate(R.layout.pyramid_item, this, false)
        addView(pyramidItem)
    }

    fun setPoint(point : Int){
        text_point.text = point.toString()
        text_point.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
    }


    private fun setLayoutSize(size : Int) {
        val params = LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val scale = resources.displayMetrics.density
        val dpPixel = (size * scale).toInt()
        params.height = dpPixel
        params.width = dpPixel
        setLayoutParams(params)
    }



}