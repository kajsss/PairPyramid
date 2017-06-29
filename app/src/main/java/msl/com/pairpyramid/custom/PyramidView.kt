package msl.com.pairpyramid.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.pyramid_layout.view.*
import msl.com.pairpyramid.R


open class PyramidView : LinearLayout {

    var playersCount = 5 // Default Player Count
    lateinit var nameList : Array<String>


    constructor(context: Context) : super(context) {
        initView()
        drawPyramid()
    }

    constructor(context: Context, playersCount : Int) : super(context) {
        this.playersCount = playersCount
        initView()
        drawPyramid()
    }

    constructor(context: Context, playersCount : Int, nameList : Array<String>) : super(context) {
        this.playersCount = playersCount
        this.nameList = nameList
        initView()
        drawPyramid()
    }



    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {
        initView()
        drawPyramid()
    }


    private fun initView() {
        setBackgroundColor(R.color.background_material_light)
        LayoutInflater.from(context).inflate(R.layout.pyramid_layout, this);
    }

    private fun drawPyramid() {

        //draw pyramid
        for(i in 1..playersCount){
            var newLine  = getNewLine(context)
            for(j in 1..i){
                newLine.addView(PyramidTriangle(context, getTriangleSize(), getFontSize(),i+j+10 ))
            }
            layout_pyramid.addView(newLine)
        }
        //draw Name list
        for(i in 0..(playersCount-1)){
            layout_name.addView(PyramidName(context,getTriangleSize(),nameList[i]))
        }

    }

    private fun  getFontSize(): Int {
        when(playersCount){
            else -> return 13
        }
    }

    private fun getTriangleSize(): Int {
        when(playersCount){
            0,1,2 -> return 0
            3     -> return 70
            4     -> return 65
            5     -> return 55
            6,7   -> return 50
            8     -> return 45
            else  -> return 40
        }
    }

    private fun getNewLine(context: Context) : LinearLayout{
        val linLayout = LinearLayout(context)
        val linLayoutParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linLayout.orientation = LinearLayout.HORIZONTAL
        linLayout.layoutParams = linLayoutParam
        return linLayout
    }


}
