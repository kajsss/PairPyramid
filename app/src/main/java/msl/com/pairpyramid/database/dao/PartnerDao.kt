package msl.com.pairpyramid.database.dao

import android.content.Context
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PartnerRowParser
import msl.com.pairpyramid.model.Partner
import org.jetbrains.anko.db.select

class PartnerDao(var context: Context) {

    val database: DatabaseHelper
        get() = DatabaseHelper.getInstance(context.applicationContext)

    fun selectAllPartnerList() : List<Partner>? {

        var partnerList : List<Partner>? = null

        database.use {
            partnerList = select("Partner").parseList(PartnerRowParser())
        }

        return partnerList
    }
}