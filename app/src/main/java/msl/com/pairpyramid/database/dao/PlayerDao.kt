package msl.com.pairpyramid.database.dao

import android.content.Context
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PlayerRowParser
import msl.com.pairpyramid.model.Player
import org.jetbrains.anko.db.select

class PlayerDao(var context: Context) {

    val database: DatabaseHelper
        get() = DatabaseHelper.getInstance(context.applicationContext)

    fun selectAllPlayerList() : List<Player>? {

        var playerList : List<Player>? = null
        database.use {
            playerList = select("Player").parseList(PlayerRowParser())
        }
        return playerList
    }
}