package msl.com.pairpyramid.view.entry

import msl.com.pairpyramid.database.dao.PartnerDao
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player

class MakeEntryPresenter constructor(override var view : MakeEntryContract.View) : MakeEntryContract.Presenter {

    lateinit override var playerList: List<Player>
    var playerDao : PlayerDao
    var partnerDao : PartnerDao

    init{
        playerDao = PlayerDao(view.getContext())
        partnerDao = PartnerDao(view.getContext())
    }

    override fun loadPlayerList(updateAdapter: (List<Player>) -> Unit) {
        playerList = playerDao.selectAllPlayerList()!!
        updateAdapter(playerList)
    }

    override fun matchingPartners(checkedPlayerList: List<Player>) : List<Partner>{

        var playerIdList = checkedPlayerList.map { it.id } .toTypedArray()
        var resultPartnerList = ArrayList<Partner>()
        var map = partnerDao.selectPairCounts()

        var completeQueue = ArrayList<Int>()
        (1 .. 8).forEach { current ->
            // 최소값인 상대랑 매핑 후 완료 큐에 밀어 넣음

            if (!playerIdList.contains(current)) return@forEach
            if (completeQueue.contains(current)) return@forEach //이미 완료된 경우

            if (completeQueue.size+1 == checkedPlayerList.size) { //혼자 남은 경우
                completeQueue.add(current)
                resultPartnerList.add(Partner(current,current))
                return@forEach
            }

            var minValue = Int.MAX_VALUE
            var minIndex = -1
            (1 .. 8).forEach { target ->

                var count = map[Pair(current, target)] ?: 0

                if (minValue > count
                        && current != target
                        && !completeQueue.contains(target)
                        && playerIdList.contains(target)){

                    minValue = count
                    minIndex = target

                }
            }

            completeQueue.add(current)
            completeQueue.add(minIndex)
            resultPartnerList.add(Partner(current,minIndex))
        }


        resultPartnerList.forEach { it -> partnerDao.insertPartner(it) }  // result Insert
        return resultPartnerList
    }
}