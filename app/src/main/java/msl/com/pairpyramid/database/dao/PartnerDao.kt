package msl.com.pairpyramid.database.dao

import android.content.Context
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PartnerRowParser
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
                        "create_date" to Date().toString())
            }
        }
        return cnt
    }

    fun selectPartnerListByPlayerIdList(playerIdList: ArrayList<Int>) :  List<Partner>? {
        var partnerList: List<Partner>? = null

        database.use{
            var query : String = ""
            playerIdList.forEach { query += it.toString() + "," }
            query = query.dropLast(1)
            partnerList = select("Partner").whereArgs("player_1 IN (${query}) AND player_2 IN (${query})").parseList(PartnerRowParser())
        }
        return partnerList
   }

}