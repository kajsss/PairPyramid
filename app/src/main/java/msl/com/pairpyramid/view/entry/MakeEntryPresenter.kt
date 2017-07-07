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
        var keepPlayerIdList = checkedPlayerList.filter { it.keep }.map { it.id } .toTypedArray()
        var resultPartnerList = ArrayList<Partner>()
        var completeIdList = ArrayList<Int>()
        var map = partnerDao.selectPairStatistics(playerDao.selectAllPlayerList()).filter {
            playerIdList.contains(it.key.first) && playerIdList.contains(it.key.second)
            && it.key.first != it.key.second
        }

        map .filter { keepPlayerIdList.contains(it.key.first).xor(keepPlayerIdList.contains(it.key.second)) }
            .forEach { (pair, pyramidInfo) ->
                matchAll(pair, completeIdList, resultPartnerList)
            }

        map.forEach { (pair, pyramidInfo) ->
            matchAll(pair, completeIdList, resultPartnerList)
        }

        playerIdList.filter { !completeIdList.contains(it) }.forEach { soloPlayer ->
            match(Pair(soloPlayer, soloPlayer), completeIdList, resultPartnerList)
        }

        return resultPartnerList
    }

    private fun matchAll(pair: Pair<Int, Int>, completeIdList: ArrayList<Int>, resultPartnerList: ArrayList<Partner>) {
        if(!completeIdList.contains(pair.first) && !completeIdList.contains(pair.second)) {
            match(pair, completeIdList, resultPartnerList)
        }
    }

    private fun match(pair: Pair<Int, Int>, completeIdList: ArrayList<Int>, resultPartnerList: ArrayList<Partner>) {
        completeIdList.add(pair.first)
        completeIdList.add(pair.second)
        resultPartnerList.add(Partner(pair.first, pair.second))
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