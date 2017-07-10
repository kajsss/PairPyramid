package msl.com.pairpyramid.database.dao

import android.content.Context
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PartnerRowParser
import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.model.PyramidInfo
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class PartnerDao(var context: Context) {

    val database: DatabaseHelper
        get() = DatabaseHelper.getInstance(context)

    fun selectAllPartnerList(): List<Partner>? {
        var partnerList: List<Partner>? = null
        database.use {
            partnerList = select("Partner").orderBy("create_date", SqlOrderDirection.DESC).parseList(PartnerRowParser())
        }
        return partnerList
    }

    fun insertPartner(vararg partners: Partner): Long {
        var cnt: Long = 0
        database.use {
            partners.forEach { partner ->
                cnt += insert("Partner",
                        "id" to partner.id,
                        "player_1" to partner.player_1,
                        "player_2" to partner.player_2,
                        "create_date" to partner.createDate)
            }
        }
        return cnt
    }

    fun selectPairCounts() : HashMap<Pair<Int, Int>, PyramidInfo> {
        var allPartnerList = selectAllPartnerList()
        var pairCountHashMap : HashMap<Pair<Int, Int>, PyramidInfo> = hashMapOf()

        getMatchingCount(allPartnerList, pairCountHashMap)
        getRecentlyPaired(allPartnerList, pairCountHashMap)

        return pairCountHashMap
    }

    private fun getMatchingCount(allPartnerList: List<Partner>?, pairCountHashMap: HashMap<Pair<Int, Int>, PyramidInfo>) {
        allPartnerList?.forEach {
            if (pairCountHashMap[Pair(it.player_1, it.player_2)] == null) {
                pairCountHashMap[Pair(it.player_1, it.player_2)] = PyramidInfo(1, false)
            } else {
                pairCountHashMap[Pair(it.player_1, it.player_2)]!!.inc()
            }
        }
    }

    private fun getRecentlyPaired(allPartnerList: List<Partner>?, pairCountHashMap: HashMap<Pair<Int, Int>, PyramidInfo>) {
        allPartnerList!!.filter { !"".equals(it.createDate) && it.createDate.equals(allPartnerList!!.get(0).createDate) }.forEach {
            pairCountHashMap[Pair(it.player_1, it.player_2)]?.recentlyPaired = true
        }
    }

    fun selectPairStatistics(playerList: List<Player>) : SortedMap<Pair<Int, Int>, PyramidInfo> {

        var allPartnerList = selectAllPartnerList()!!.sortedByDescending{
            it.createDate
        }

        var pairStatisticsHashMap: HashMap<Pair<Int, Int>, PyramidInfo> = initPairHashMap(playerList)

        allPartnerList?.forEach{
            if(pairStatisticsHashMap[Pair(it.player_1, it.player_2)] == null){
                pairStatisticsHashMap[Pair(it.player_1, it.player_2)] = PyramidInfo(0, false,"19000101000000")
            }
            var pairStatistics = PyramidInfo(pairStatisticsHashMap[Pair(it.player_1, it.player_2)]!!.count!!.inc(), false, it.createDate)
            pairStatisticsHashMap.set(Pair(it.player_1, it.player_2), pairStatistics)
        }

        return pairStatisticsHashMap.toSortedMap(compareBy { key->  pairStatisticsHashMap[key] })

    }

    private fun initPairHashMap(playerList: List<Player>): HashMap<Pair<Int, Int>, PyramidInfo> {
        var pairStatisticsHashMap: HashMap<Pair<Int, Int>, PyramidInfo> = hashMapOf()

        playerList.map { it.id }.forEach { f ->
            playerList.map { it.id }.forEach { s ->
                if (f < s) {
                    pairStatisticsHashMap[Pair(f, s)] = PyramidInfo(0, false, "19000101000000")
                }
            }
        }
        return pairStatisticsHashMap
    }
}