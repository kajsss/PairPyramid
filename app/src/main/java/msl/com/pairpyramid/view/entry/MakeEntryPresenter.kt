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

    override fun matchingPartners(players: List<Player>) : List<Partner>{

        var map = Array(9, {IntArray(9, { i->0 })})
        var resultPartnerList = ArrayList<Partner>()
        var playerIdList = ArrayList<Int>()

        players.forEach { playerIdList.add(it.id) }
        val partnerList = partnerDao.selectPartnerListByPlayerIdList(playerIdList)

        partnerList!!.forEach {
            var player1 = it.player_1
            var player2 = it.player_2

            if( player1 == player2) {
                map[player1][player2]++
                return@forEach
            }
            map[player1][player2]++
            map[player2][player1]++
        }

        var completeQueue = ArrayList<Int>()
        (1 .. 8).forEach { current ->
            // 최소값인 상대랑 매핑 후 완료 큐에 밀어 넣음

            if (!playerIdList.contains(current)) return@forEach
            if (completeQueue.contains(current)) return@forEach //이미 완료된 경우

            if (completeQueue.size+1 == players.size) { //혼자 남은 경우
                completeQueue.add(current)
                resultPartnerList.add(Partner(current,current))
                return@forEach
            }


            var minValue = Int.MAX_VALUE
            var minIndex = -1

            (1 .. 8).forEach { target ->


                if (minValue > map[current][target]
                        && current != target
                        && !completeQueue.contains(target)
                        && playerIdList.contains(target)){

                    minValue = map[current][target]
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