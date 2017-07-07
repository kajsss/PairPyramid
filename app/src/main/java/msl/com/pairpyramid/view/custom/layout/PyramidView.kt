package msl.com.pairpyramid.view.custom.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.pyramid_layout.view.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.model.PyramidInfo


open class PyramidView : LinearLayout {

    var playersCount = 5 // Default Player Count
    lateinit var activePlayerList: Array<Player>
    lateinit var pairCountsHashMap: HashMap<Pair<Int, Int>, PyramidInfo>

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {
        initView()
        drawPyramid()
    }

    constructor(context: Context, activePlayerList: Array<Player>, pairCountsHashMap : HashMap<Pair<Int, Int>, PyramidInfo>) : super(context) {
        this.playersCount = activePlayerList.size
        this.activePlayerList = activePlayerList
        this.pairCountsHashMap = pairCountsHashMap
        initView()
        drawPyramid()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.pyramid_layout, this);
    }

    private fun drawPyramid() {
        //draw pyramid
        for(i in 1..playersCount){
            var newLine  = getNewLine(context)
            for(j in 1..i){
                var pyramidInfo = pairCountsHashMap[Pair(activePlayerList[j-1].id, activePlayerList[playersCount - i + j-1].id )]?: PyramidInfo()

                newLine.addView(PyramidTriangle(context, getTriangleSize(), getFontSize(), pyramidInfo))
            }
            layout_pyramid.addView(newLine)
        }
        //draw Name list
        for(i in 0 until playersCount){
            layout_name.addView(PyramidName(context, getTriangleSize(), activePlayerList[i].name))
        }
    }

    private fun  getFontSize(): Int {
        when(playersCount){
            else -> return 13
        }
    }

    private fun getTriangleSize(): Int {
        when(playersCount){
            0     -> return 0
            1,2   -> return 100
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
