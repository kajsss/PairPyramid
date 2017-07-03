package msl.com.pairpyramid.database.parser

import msl.com.pairpyramid.model.Partner
import org.jetbrains.anko.db.RowParser

class PartnerRowParser : RowParser<Partner> {
    override fun parseRow(columns: Array<Any?>): Partner {
        return Partner(columns[1] as String, columns[2] as String).apply {
            id = columns[0] as String
        }
    }
}