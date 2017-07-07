package msl.com.pairpyramid.view.pyramid

import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.model.PyramidInfo

interface PairPyramidContract {
    interface Presenter {
        var view : PairPyramidContract.View

        fun getPairPyramidData()
    }

    interface View {
        fun getContext() : android.content.Context
        fun drawPyramid(activePlayerList: Array<Player>, pairCountsHashMap : HashMap<Pair<Int, Int>, PyramidInfo>)
    }
}