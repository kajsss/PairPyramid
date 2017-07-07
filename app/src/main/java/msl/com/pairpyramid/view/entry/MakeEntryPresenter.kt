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

    override fun loadPlayerList() {
        playerList = playerDao.selectAllPlayerList()!!
        view.updateAdapter(playerList)
    }

    override fun matchingPartners(checkedPlayerList: List<Player>) : List<Partner>{


        var playerIdList = checkedPlayerList.map { it.id } .toTypedArray()
        var resultPartnerList = ArrayList<Partner>()
        var map = partnerDao.selectPairStatistics()

        var completeQueue = ArrayList<Int>()
        map.forEach { (t, u) ->
            if(completeQueue.size >= playerIdList.size-1) return@forEach
            if(!playerIdList.contains(t.first) || !playerIdList.contains(t.second)) return@forEach
            if(completeQueue.contains(t.first) || completeQueue.contains(t.second)) return@forEach
            if(t.first == t.second) return@forEach

            completeQueue.add(t.first)
            completeQueue.add(t.second)
            resultPartnerList.add(Partner(t.first, t.second))
        }

        if(completeQueue.size != playerIdList.size) {
            playerIdList.filter { !completeQueue.contains(it) }.forEach { soloPlayer ->
                completeQueue.add(soloPlayer)
                completeQueue.add(soloPlayer)
                resultPartnerList.add(Partner(soloPlayer, soloPlayer))
            }
        }

        return resultPartnerList
    }

    override fun insertPartners(matchingPartners: List<Partner>) {
        matchingPartners.forEach { it -> partnerDao.insertPartner(it) }
    }

    override fun getPartnerText(partner: Partner): String {
        return when(partner.player_1 == partner.player_2) {
            true -> playerDao.selectPlayerNameById(partner.player_1) + " Solo!! "
            else -> playerDao.selectPlayerNameById(partner.player_1) + " ♡ " + playerDao.selectPlayerNameById(partner.player_2)
        }
    }
}