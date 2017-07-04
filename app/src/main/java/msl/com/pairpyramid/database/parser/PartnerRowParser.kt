package msl.com.pairpyramid.database.parser

import msl.com.pairpyramid.model.Partner
import org.jetbrains.anko.db.RowParser

class PartnerRowParser : RowParser<Partner> {
    override fun parseRow(columns: Array<Any?>): Partner {
        return Partner((columns[1] as Long).toInt(), (columns[2] as Long).toInt()).apply {
            id = columns[0] as String
        }
    }
}