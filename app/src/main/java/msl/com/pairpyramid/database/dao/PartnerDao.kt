package msl.com.pairpyramid.database.dao

import android.content.Context
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PartnerRowParser
import msl.com.pairpyramid.model.PairStatistics
import msl.com.pairpyramid.model.Partner
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class PartnerDao(var context: Context) {

    val database: DatabaseHelper
        get() = DatabaseHelper.getInstance(context)

    fun selectAllPartnerList(): List<Partner>? {
        var partnerList: List<Partner>? = null
        database.use {
            partnerList = select("Partner").parseList(PartnerRowParser())
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

    fun selectPairCounts() : HashMap<Pair<Int, Int>, Int> {
        var allPartnerList = selectAllPartnerList()
        var pairCountHashMap : HashMap<Pair<Int, Int>, Int> = hashMapOf()

        allPartnerList?.forEach{
            if(pairCountHashMap[Pair(it.player_1, it.player_2)] == null) {
                pairCountHashMap[Pair(it.player_1, it.player_2)] = 1}
            else{
                pairCountHashMap.set(Pair(it.player_1, it.player_2), pairCountHashMap[Pair(it.player_1, it.player_2)]!!.inc())
            }
        }

        return pairCountHashMap
    }

    fun selectPairStatistics() : SortedMap<Pair<Int, Int>, PairStatistics> {
        var allPartnerList = selectAllPartnerList()!!.sortedByDescending{
            it.createDate
        }
        var pairStatisticsHashMap : HashMap<Pair<Int, Int>, PairStatistics> = hashMapOf()

        (1 .. 8).forEach { f ->
            (f .. 8).forEach { s ->
                pairStatisticsHashMap[Pair(f, s)] = PairStatistics(0, "19000101000000")
            }
        }

        allPartnerList?.forEach{
            var pairStatistics = PairStatistics(pairStatisticsHashMap[Pair(it.player_1, it.player_2)]!!.count.inc(), it.createDate)
            pairStatisticsHashMap.set(Pair(it.player_1, it.player_2), pairStatistics)
        }

//        allPartnerList.forEach { println(it.toString() + " : " + it.createDate) }
//
//        println(pairStatisticsHashMap.size.toString())
//
//        pairStatisticsHashMap.toSortedMap(compareBy { pairStatisticsHashMap[it] }).forEach { (t, u) -> println(t.first.toString() + "," + t.second.toString() + " -> " + u.toString()
//        ) }
//
////        pairStatisticsHashMap.
//
//        println(pairStatisticsHashMap.toSortedMap(compareBy { pairStatisticsHashMap[it] }).size.toString())

        return pairStatisticsHashMap.toSortedMap(compareBy { pairStatisticsHashMap[it] })

    }
}