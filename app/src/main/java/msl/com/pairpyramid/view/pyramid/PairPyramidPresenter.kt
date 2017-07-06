package msl.com.pairpyramid.view.pyramid

import msl.com.pairpyramid.database.dao.PartnerDao
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.PyramidInfo

class PairPyramidPresenter constructor(override var view: PairPyramidContract.View) : PairPyramidContract.Presenter {

    lateinit var activePlayerNameList: Array<String>
    lateinit var pairCountsHashMap: HashMap<Pair<Int, Int>, PyramidInfo>

    override fun getPairPyramidData() {
        activePlayerNameList = PlayerDao(view.getContext()).selectAllPlayerList()!!
                .filter { it.useYn }
                .map{ it.name }
                .toTypedArray()
        pairCountsHashMap = PartnerDao(view.getContext()).selectPairCounts()

        view.drawPyramid(activePlayerNameList, pairCountsHashMap)
    }
}
