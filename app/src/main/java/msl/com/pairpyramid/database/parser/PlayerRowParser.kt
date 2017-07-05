package msl.com.pairpyramid.database.parser

import msl.com.pairpyramid.model.Player
import org.jetbrains.anko.db.RowParser

class PlayerRowParser : RowParser<Player> {
    override fun parseRow(columns: Array<Any?>): Player {
        if(columns[4] != null){
            return Player((columns[0] as Long).toInt(), columns[1] as String, columns[2] as String, columns[4]  as ByteArray)
        }else{
            return Player((columns[0] as Long).toInt(), columns[1] as String, columns[2] as String)
        }
    }
}