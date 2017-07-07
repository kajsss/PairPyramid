package msl.com.pairpyramid.view.pyramid

import msl.com.pairpyramid.database.dao.PartnerDao
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.model.PyramidInfo

class PairPyramidPresenter constructor(override var view: PairPyramidContract.View) : PairPyramidContract.Presenter {

    lateinit var activePlayerList: Array<Player>
    lateinit var pairCountsHashMap: HashMap<Pair<Int, Int>, PyramidInfo>

    override fun getPairPyramidData() {
        activePlayerList = PlayerDao(view.getContext()).selectAllPlayerList()!!
                .toTypedArray()

        pairCountsHashMap = PartnerDao(view.getContext()).selectPairCounts()

        view.drawPyramid(activePlayerList, pairCountsHashMap)
    }
}
